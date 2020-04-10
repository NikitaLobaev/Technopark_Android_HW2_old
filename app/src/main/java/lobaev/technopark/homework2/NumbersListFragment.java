package lobaev.technopark.homework2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

public class NumbersListFragment extends Fragment implements View.OnClickListener {
	
	static final int defaultCount = 100;
	private int count = defaultCount;
	private RecyclerView recyclerView;
	private MyAdapter myAdapter = null;
	
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		if (savedInstanceState != null) {
			count = savedInstanceState.getInt("count", defaultCount);
		}
		View view = inflater.inflate(R.layout.fragment_numberslist, container, false);
		recyclerView = view.findViewById(R.id.list_RecyclerView);
		myAdapter = new MyAdapter();
		recyclerView.setAdapter(myAdapter);
		view.findViewById(R.id.list_Button_add).setOnClickListener(this);
		return view;
	}
	
	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		outState.putInt("count", count);
		super.onSaveInstanceState(outState);
	}
	
	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.list_Button_add) {
			myAdapter.addNumber();
			recyclerView.scrollToPosition(count - 1);
		} else if (v instanceof TextView && getActivity() != null) {
			TextView textView = (TextView) v;
			FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager()
					                                          .beginTransaction();
			NumberFragment numberFragment = new NumberFragment();
			Bundle bundle = new Bundle();
			bundle.putInt("number", Integer.parseInt(textView.getText().toString()));
			numberFragment.setArguments(bundle);
			fragmentTransaction.replace(R.id.main_FrameLayout, numberFragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		}
	}
	
	public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
		
		@NonNull
		@Override
		public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
			return new MyViewHolder((TextView) LayoutInflater.from(parent.getContext())
					.inflate(R.layout.fragment_numberslist_number, parent, false));
		}
		
		@Override
		public void onBindViewHolder(MyViewHolder holder, int position) {
			TextView textView = holder.getTextView();
			textView.setText(String.valueOf(position + 1));
			textView.setTextColor(getResources().getColor(position % 2 != 0 ? R.color.even : R.color.odd));
			textView.setOnClickListener(NumbersListFragment.this);
		}
		
		@Override
		public int getItemCount() {
			return count;
		}
		
		private class MyViewHolder extends RecyclerView.ViewHolder {
			
			private TextView textView;
			
			MyViewHolder(TextView textView) {
				super(textView);
				this.textView = textView;
			}
			
			TextView getTextView() {
				return textView;
			}
			
		}
		
		void addNumber() {
			count++;
			notifyDataSetChanged();
		}
		
	}
	
}
