package jerryartist.com.aikidodojofinder;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import jerryartist.com.aikidodojofinder.dummy.DummyContent;

/**
 * A fragment representing a single Seminar detail screen.
 * This fragment is either contained in a {@link SeminarListActivity}
 * in two-pane mode (on tablets) or a {@link SeminarDetailActivity}
 * on handsets.
 */
public class SeminarDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The content this fragment is presenting.
     */
    private SeminarContent.SeminarItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SeminarDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = SeminarContent.ITEM_MAP.get(getArguments().getInt(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seminar_detail, container, false);

        // Show the content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.seminar_detail)).setText(mItem.content);
        }

        return rootView;
    }
}
