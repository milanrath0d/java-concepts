package org.milan.observable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Milan Rathod
 */
class ObservableTest {

    @Test
    public void whenChangingPCLNewsAgencyState_thenONewsChannelNotified() {
        NewsAgency observable = new NewsAgency();
        NewsChannel observer = new NewsChannel();

        observable.addPropertyChangeListener(observer);

        observable.setNews("news");
        assertEquals(observer.getNews(), "news");
    }

}