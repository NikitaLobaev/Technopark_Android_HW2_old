package lobaev.technopark.homework2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class NumberFragment extends Fragment {
	
	private int number = NumbersListFragment.defaultCount;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		TextView textView = (TextView) inflater.inflate(R.layout.fragment_number, container, false);
		if (getArguments() != null) {
			number = getArguments().getInt("number");
		} else if (savedInstanceState != null) {
			number = savedInstanceState.getInt("number");
		}
		textView.setText(String.valueOf(number));
		textView.setTextColor(getResources().getColor(number % 2 == 0 ? R.color.even : R.color.odd));
		return textView;
	}
	
	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		outState.putInt("number", number);
		super.onSaveInstanceState(outState);
	}
	
}
