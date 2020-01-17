package com.keeyoshi.foodmandu.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.keeyoshi.foodmandu.R;
import com.keeyoshi.foodmandu.api.AlcoholApi;
import com.keeyoshi.foodmandu.api.NewMembersApi;
import com.keeyoshi.foodmandu.api.Super;
import com.keeyoshi.foodmandu.model.Alcohol;
import com.keeyoshi.foodmandu.model.Bakery;
import com.keeyoshi.foodmandu.model.Detail;
import com.keeyoshi.foodmandu.model.Food;
import com.keeyoshi.foodmandu.model.Member;
import com.keeyoshi.foodmandu.ui.AlcoholAdapter;
import com.keeyoshi.foodmandu.ui.BakeryAdapter;
import com.keeyoshi.foodmandu.ui.DetailAdapter;
import com.keeyoshi.foodmandu.ui.FoodAdapter;
import com.keeyoshi.foodmandu.ui.MembersAdapter;
import com.keeyoshi.foodmandu.url.Url;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    List<Detail> detailList;
    DetailAdapter DetailAdapter;

    List<Member> memberList;
    MembersAdapter MembersAdapter;
    ImageView imgmember;

    List<Alcohol> alcoholList;
    AlcoholAdapter AlcoholAdapter;

    private HomeViewModel homeViewModel;
    //binding
    ImageView card1,card2;

    private int[] mImages=new int[]{
            R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d
    };
    private String [] mImageTitle=new String[]{
            "Liquor","MoMo","Sauce","juice"
    };
    private RecyclerView recyclerView,recyclerView_a,recyclerView_b,recyclerView_c,recyclerView_d;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_home,container,false);

        CarouselView carouselView;
        carouselView=view.findViewById(R.id.caral);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), mImageTitle[position], Toast.LENGTH_SHORT).show();
            }
        });


        recyclerView=view.findViewById(R.id.recyclerView);
        recyclerView_b=view.findViewById(R.id.recyclerView_b);
        recyclerView_c=view.findViewById(R.id.recyclerView_c);
        recyclerView_d=view.findViewById(R.id.recyclerView_d);

        card1=view.findViewById(R.id.card1);
        card2=view.findViewById(R.id.card2);

        List<Food> foodList=new ArrayList<>();
        foodList.add(new Food("Restaurants",R.drawable.res));
        foodList.add(new Food("Liquors",R.drawable.liquor));
        foodList.add(new Food("Bakeries",R.drawable.cup));
        foodList.add(new Food("Refreshments",R.drawable.ref));
        foodList.add(new Food("Organics",R.drawable.o));


        FoodAdapter foodAdapter=new FoodAdapter(getContext(),foodList);
        recyclerView.setAdapter(foodAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

        List<Bakery> cakeList=new ArrayList<>();
        cakeList.add(new Bakery(R.drawable.blue));
        cakeList.add(new Bakery(R.drawable.pas));
        cakeList.add(new Bakery(R.drawable.chic));
        cakeList.add(new Bakery(R.drawable.cross));

        BakeryAdapter bakeryAdapter=new BakeryAdapter(getContext(),cakeList);
        recyclerView_b.setAdapter(bakeryAdapter);
        recyclerView_b.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));


//        homeViewModel =
//                ViewModelProviders.of(this).get(HomeViewModel.class);
//        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });

        newmember();
        alcohol();
        return view;
    }

    private void super7(){
        detailList=new ArrayList<>();

        Super s= Url.getInstance().create(Super.class);
        Call<List<Detail>> listCall=s.getSuper7();
        listCall.enqueue(new Callback<List<Detail>>() {
            @Override
            public void onResponse(Call<List<Detail>> call, Response<List<Detail>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Detail> detailList1=response.body();
                DetailAdapter =new DetailAdapter(getContext(),detailList1);

                recyclerView_a.setAdapter(DetailAdapter);
                recyclerView_a.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
                recyclerView_a.setHasFixedSize(true);

            }

            @Override
            public void onFailure(Call<List<Detail>> call, Throwable t) {

                Log.d("Error Message","Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void newmember(){
        memberList = new ArrayList();
        NewMembersApi membersApi=Url.getInstance().create(NewMembersApi.class);

        Call<List<Member>> listCall4= membersApi.getNewMembers();

        listCall4.enqueue(new Callback<List<Member>>() {
            @Override
            public void onResponse(Call<List<Member>> call, Response<List<Member>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Member> memberList1=response.body();
                MembersAdapter = new MembersAdapter(getContext(),memberList1);
                recyclerView_c.setAdapter(MembersAdapter);
                recyclerView_c.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
            }

            @Override
            public void onFailure(Call<List<Member>> call, Throwable t) {

                Log.d("Error message","Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void alcohol(){
        alcoholList = new ArrayList();
        AlcoholApi alcoholApi=Url.getInstance().create(AlcoholApi.class);

        Call<List<Alcohol>> listCall3= alcoholApi.getAlcohol();

        listCall3.enqueue(new Callback<List<Alcohol>>() {
            @Override
            public void onResponse(Call<List<Alcohol>> call, Response<List<Alcohol>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getContext(), "Error" + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }

                List<Alcohol> alcoholList1=response.body();
                AlcoholAdapter = new AlcoholAdapter(getContext(),alcoholList1);
                recyclerView_d.setAdapter(AlcoholAdapter);
                recyclerView_d.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onFailure(Call<List<Alcohol>> call, Throwable t) {
                Log.d("Error message","Error" + t.getLocalizedMessage());
                Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

    }
}