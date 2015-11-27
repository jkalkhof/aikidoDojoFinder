package jerryartist.com.aikidodojofinder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 11/26/15.
 */
public class SeminarContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<SeminarItem> ITEMS = new ArrayList<SeminarItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<Integer, SeminarItem> ITEM_MAP = new HashMap<Integer, SeminarItem>();

    public static void addItem(SeminarItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class SeminarItem {
        public int id;
        public String content;
        public String title;

        public SeminarItem(int id, String title, String content) {
            this.id = id;
            this.title = title;
            this.content = content;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
