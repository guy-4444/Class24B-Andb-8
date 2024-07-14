package com.guy.class24b_andb_8;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;

import com.guy.class24b_andb_8.PathImageView.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PathImageView pathImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pathImageView = findViewById(R.id.map1);


        List<LatLng> latLngList = generatePath();
        pathImageView.setLatLngList(latLngList);
    }

    private List<LatLng> generatePath() {
        List<LatLng> latLngList = new ArrayList<>();
        latLngList.add(new LatLng(32.11373217418622, 34.81242273845702, .0));
        latLngList.add(new LatLng(32.11426997770281, 34.81244269705004, 5.0));
        latLngList.add(new LatLng(32.11524230418772, 34.81227103567735, 6.0));
        latLngList.add(new LatLng(32.115542178490635, 34.813118613704965, 7.0));
        latLngList.add(new LatLng(32.11473342432851, 34.813451207614534, 12.0));
        latLngList.add(new LatLng(32.114996951752616, 34.814513362358, 13.0));
        latLngList.add(new LatLng(32.11530591397065, 34.815049804147634, 23.0));
        latLngList.add(new LatLng(32.115887487073635, 34.81585446683207, 35.0));
        latLngList.add(new LatLng(32.11589657412398, 34.81586519566787, 45.0));
        latLngList.add(new LatLng(32.11553309140503, 34.8171097406198, 46.0));
        latLngList.add(new LatLng(32.11365204520856, 34.816798604381816, 50.0));
        latLngList.add(new LatLng(32.11351573600702, 34.81850448927283, 68.0));
        latLngList.add(new LatLng(32.113441441764394, 34.81861638266612, 45.0));
        latLngList.add(new LatLng(32.11339657790949, 34.81867558231983, 31.0));
        latLngList.add(new LatLng(32.11342032936483, 34.81879709739848, 21.0));
        latLngList.add(new LatLng(32.11354568416569, 34.81883292876783, 10.0));
        latLngList.add(new LatLng(32.11357867224259, 34.81874880294415, 5.0));
        latLngList.add(new LatLng(32.11374493196863, 34.818798655284105, 0.0));
        latLngList.add(new LatLng(32.11441084445996, 34.81890381547987, 5.0));
        latLngList.add(new LatLng(32.11452300280976, 34.81808436764174, 17.0));
        latLngList.add(new LatLng(32.11462592446832, 34.81807657821361, 34.0));
        latLngList.add(new LatLng(32.11474599959365, 34.8173599508068, 67.0));
        latLngList.add(new LatLng(32.11473676305144, 34.81732411943745, 90.0));
        latLngList.add(new LatLng(32.114695858353265, 34.81726647766937, 98.0));
        latLngList.add(new LatLng(32.11460349283834, 34.81727738286874, 76.0));
        latLngList.add(new LatLng(32.11453487839532, 34.817512623597935, 45.0));
        return latLngList;
    }
}