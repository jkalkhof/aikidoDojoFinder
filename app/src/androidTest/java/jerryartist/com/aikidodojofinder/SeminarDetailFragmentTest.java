package jerryartist.com.aikidodojofinder;

import android.content.Intent;
import android.test.InstrumentationTestCase;
import android.util.Log;

public class SeminarDetailFragmentTest extends InstrumentationTestCase {

    private static final String TAG = "SeminarListFragmentTest";
    Intent mLaunchIntent = null;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mLaunchIntent = new Intent(getInstrumentation()
                .getTargetContext(), SeminarListFragment.class);

        mLaunchIntent.putExtra(SeminarDetailFragment.ARG_ITEM_ID,1);

        launchActivityWithIntent("jerryartist.com.aikidodojofinder", SeminarDetailActivity.class , mLaunchIntent);

    }


    public void testActivityWasLaunchedWithIntent() {

        Log.d(TAG, "testActivityWasLaunchedWithIntent");

        final int payload =
                mLaunchIntent.getIntExtra(SeminarDetailFragment.ARG_ITEM_ID, -1);

        //assertEquals("Payload is invalid", -1, payload);
        assertEquals("Payload is invalid", 1, payload);
    }
}