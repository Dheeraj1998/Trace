package com.example.trace;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class AnalysisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        setTheme(R.style.AppTheme);

        GraphView graph_1 = findViewById(R.id.graph_1);
        LineGraphSeries<DataPoint> series_1 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3)
        });
        series_1.setAnimated(true);
        series_1.setThickness(8);
        series_1.setColor(Color.CYAN);

        graph_1.setTitle("Body Temperature");
        graph_1.setTitleColor(Color.WHITE);
        graph_1.getLegendRenderer().setVisible(true);
        graph_1.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph_1.addSeries(series_1);

        GraphView graph_2 = findViewById(R.id.graph_2);
        BarGraphSeries<DataPoint> series_2 = new BarGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 22),
                new DataPoint(1, 37),
                new DataPoint(2, 45)
        });
        graph_2.setTitle("Stress Levels");
        graph_2.setTitleColor(Color.WHITE);
        graph_2.addSeries(series_2);

        GraphView graph_3 = findViewById(R.id.graph_3);
        LineGraphSeries<DataPoint> series_3 = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 5),
                new DataPoint(1, 2),
                new DataPoint(2, 3)
        });
        series_3.setAnimated(true);
        series_3.setThickness(8);
        series_3.setColor(Color.RED);

        graph_3.setTitle("Humidity");
        graph_3.setTitleColor(Color.WHITE);
        graph_3.getLegendRenderer().setVisible(true);
        graph_3.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph_3.addSeries(series_3);
    }
}
