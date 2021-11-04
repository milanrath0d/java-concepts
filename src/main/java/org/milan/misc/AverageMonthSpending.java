package org.milan.misc;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * HackerRank problem:
 * {@link @https://www.chegg.com/homework-help/questions-and-answers/python-3-autocomplete-ready-o-2-transaction-average-monthly-spending-num-1-bin-python3-10--q56497460}
 *
 * @author Milan Rathod
 */
public class AverageMonthSpending {

    public static void main(String[] args) {
        System.out.println(getUserTransaction(4, "debit", "02-2019"));
    }

    public static List<Integer> getUserTransaction(int uid, String txnType, String monthYear) {

        List<Double> totalDebitTransactions = new ArrayList<>();

        int startPage = 1;

        int totalPages = Integer.MAX_VALUE;

        while (startPage <= totalPages) {

            try {

                JsonObject jsonObject = getData(uid, txnType, startPage);

                assert jsonObject != null;

                JsonArray data = jsonObject.getAsJsonArray("data");

                for (int i = 0; i < data.size(); i++) {
                    JsonObject current = data.get(i).getAsJsonObject();
                    long timestamp = current.get("timestamp").getAsLong();
                    String amountInString = current.get("amount").getAsString();

                    if (monthYear.equals(convertTimestamp(timestamp))) {
                        double amount = convertAmount(amountInString);
                        totalDebitTransactions.add(amount);
                    }
                }

                totalPages = jsonObject.get("total_pages").getAsInt();

                startPage++;

            } catch (Exception e) {
                System.out.println("Exception Thrown");
            }

        }

        double avg = totalDebitTransactions.stream().mapToDouble(d -> d).average().orElse(0.0);

        return getResult(uid, txnType, monthYear, avg);
    }

    public static List<Integer> getResult(int uid, String txnType, String monthYear, double avg) {

        List<Integer> output = new ArrayList<>();

        int startPage = 1;

        int totalPages = Integer.MAX_VALUE;

        while (startPage <= totalPages) {

            try {
                JsonObject jsonObject = getData(uid, txnType, startPage);

                assert jsonObject != null;

                JsonArray data = jsonObject.getAsJsonArray("data");

                for (int i = 0; i < data.size(); i++) {
                    JsonObject current = data.get(i).getAsJsonObject();
                    int id = current.get("id").getAsInt();
                    long timestamp = current.get("timestamp").getAsLong();
                    String amountInString = current.get("amount").getAsString();

                    if (monthYear.equals(convertTimestamp(timestamp))) {
                        double amount = convertAmount(amountInString);

                        if (amount > avg) {
                            output.add(id);
                        }
                    }
                }

                totalPages = jsonObject.get("total_pages").getAsInt();

                startPage++;

            } catch (Exception e) {
                System.out.println("Exception Thrown");
            }

        }

        return output;
    }

    private static JsonObject getData(int uid, String txnType, int startPage) {
        try {
            URL url = new URL("https://jsonmock.hackerrank.com/api/transactions/search?userId=" + uid +
                    "&page=" + startPage + "&txnType=" + txnType);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            httpURLConnection.setRequestMethod("GET");

            InputStream inputStream = httpURLConnection.getInputStream();

            Gson gson = new Gson();

            return gson.fromJson(new InputStreamReader(inputStream), JsonObject.class);
        } catch (Exception e) {
            System.out.println("Exception Thrown");
            return null;
        }
    }

    private static double convertAmount(String amount) throws ParseException {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        Number number = format.parse(amount);
        return number.doubleValue();
    }

    private static String convertTimestamp(long timestamp) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp);
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH) + 1;
        return month < 9 ? "0" + month + "-" + year : month + "-" + year;
    }

}
