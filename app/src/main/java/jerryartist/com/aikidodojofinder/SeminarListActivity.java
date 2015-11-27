package jerryartist.com.aikidodojofinder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import jerryartist.com.aikidodojofinder.rss.RSSFeed;
import jerryartist.com.aikidodojofinder.rss.RSSItem;
import jerryartist.com.aikidodojofinder.rss.RSSLoader;


/**
 * An activity representing a list of Seminars. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link SeminarDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link SeminarListFragment} and the item details
 * (if present) is a {@link SeminarDetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link SeminarListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class SeminarListActivity extends Activity
        implements SeminarListFragment.Callbacks {

    final static String TAG = "SeminarListActivity";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar_list);

        if (findViewById(R.id.seminar_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((SeminarListFragment) getFragmentManager()
                    .findFragmentById(R.id.seminar_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.

        String[] urlStr = {"http://www.aikiweb.com/rss/seminars.rdf?format=RSS2.0"};

        fetchRSS(urlStr);
    }

    void fetchRSS(String[] uris)
    {
        RSSLoader loader = RSSLoader.fifo();
        for (String uri : uris) {
            loader.load(uri);
        }

        Future<RSSFeed> future;
        RSSFeed feed;
        try {
            for (int i = 0; i < uris.length; i++) {
                future = loader.take();
                try {
                    feed = future.get();
                    useFeed(feed);
                } catch (ExecutionException ex1) {
                    Log.v(TAG, "Exception: " + ex1.getMessage());
                }
            }
        } catch (InterruptedException ex2) {
            Log.v(TAG, "Exception: " + ex2.getMessage());
        }

    }

    void useFeed(RSSFeed feed) {

        List<RSSItem> rssItemList = feed.getItems();
        Log.d(TAG, "useFeed: items: " + rssItemList.size());

        //for (RSSItem rssItem : rssItemList) {
        for (int i = 0; i < rssItemList.size(); i++) {
            RSSItem rssItem = rssItemList.get(i);
            Log.d(TAG, "useFeed: item["+i+"]: " + rssItem.toString());

            SeminarContent.SeminarItem seminarItem = new SeminarContent.SeminarItem(i,
                    rssItem.getCategories().get(0),
                    rssItem.getDescription()
            );
            SeminarContent.addItem(seminarItem);
        }


    }




     @Override
     public void onItemSelected(Integer id) {
         if (mTwoPane) {
             // In two-pane mode, show the detail view in this activity by
             // adding or replacing the detail fragment using a
             // fragment transaction.
             Bundle arguments = new Bundle();
             //arguments.putString(SeminarDetailFragment.ARG_ITEM_ID, id);
             arguments.putInt(SeminarDetailFragment.ARG_ITEM_ID, id);
             SeminarDetailFragment fragment = new SeminarDetailFragment();
             fragment.setArguments(arguments);
             getFragmentManager().beginTransaction()
                     .replace(R.id.seminar_detail_container, fragment)
                     .commit();

         } else {
             // In single-pane mode, simply start the detail activity
             // for the selected item ID.
             Intent detailIntent = new Intent(this, SeminarDetailActivity.class);
             detailIntent.putExtra(SeminarDetailFragment.ARG_ITEM_ID, id);
             startActivity(detailIntent);
         }
     }
}
