package com.guy.class24b_andb_8;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.List;

public class PathImageView extends View {

    public static class LatLng {
        double Latitude;
        double Longitude;
        double speed = 0;

        public LatLng(double latitude, double longitude) {
            Latitude = latitude;
            Longitude = longitude;
        }

        public LatLng(double latitude, double longitude, double speed) {
            Latitude = latitude;
            Longitude = longitude;
            this.speed = speed;
        }
    }

    public PathImageView(Context context) {
        super(context);
    }

    public PathImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PathImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PathImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, 0, 0, null);
        }
    }


    private List<LatLng> latLngList;
    private Bitmap bitmap;
    private int viewWidth = 0;
    private int viewHeight = 0;


    public void setLatLngList(List<LatLng> latLngList) {
        this.latLngList = latLngList;
        generatePath();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        viewHeight = h;
        generatePath();
    }

    public void generatePath() {
        if (latLngList == null || latLngList.size() < 1) {
            return;
        }

        if (viewWidth == 0 || viewHeight == 0) {
            return;
        }

        Paint drawPaint = new Paint();
        Paint BGPaint = new Paint();
        Paint EndDotdrawPaint = new Paint();
        Paint EndDotBGPaint = new Paint();

        double MinLatitude = 0;
        double MaxLatitude = 0;
        double MinLongitude = 0;
        double MaxLongitude = 0;
        double Distance_Proportion;
        double DrawScale;
        double Lat_Offset;
        double Lon_Offset;

        boolean isFirst = true;
        for (LatLng latLng : latLngList) {
            if (isFirst) {
                isFirst = false;
                MinLatitude = latLng.Latitude;
                MaxLatitude = latLng.Latitude;
                MinLongitude = latLng.Longitude;
                MaxLongitude = latLng.Longitude;
            } else {
                MinLatitude = Math.min(MinLatitude, latLng.Latitude);
                MaxLatitude = Math.max(MaxLatitude, latLng.Latitude);
                MinLongitude = Math.min(MinLongitude, latLng.Longitude);
                MaxLongitude = Math.max(MaxLongitude, latLng.Longitude);
            }
        }

        float thumbLineWidth = 1.0f;
        int Size = viewWidth;
        int Margin = (int) Math.ceil(thumbLineWidth * 3);
        int Size_Minus_Margins = Size - 2 * Margin;

        int color_in_line = Color.WHITE;
        int color_line_stroke = Color.GRAY;
        int color_dest_point = Color.RED;

        drawPaint.setColor(color_in_line);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(thumbLineWidth * 10);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        BGPaint.setColor(color_line_stroke);
        BGPaint.setAntiAlias(true);
        BGPaint.setStrokeWidth(thumbLineWidth * 3);
        BGPaint.setStyle(Paint.Style.STROKE);
        BGPaint.setStrokeJoin(Paint.Join.ROUND);
        BGPaint.setStrokeCap(Paint.Cap.ROUND);

        EndDotdrawPaint.setColor(color_in_line);
        EndDotdrawPaint.setAntiAlias(true);
        EndDotdrawPaint.setStrokeWidth(thumbLineWidth * 2.5f);
        EndDotdrawPaint.setStyle(Paint.Style.STROKE);
        EndDotdrawPaint.setStrokeJoin(Paint.Join.ROUND);
        EndDotdrawPaint.setStrokeCap(Paint.Cap.ROUND);

        EndDotBGPaint.setColor(color_dest_point);
        EndDotBGPaint.setAntiAlias(true);
        EndDotBGPaint.setStrokeWidth(thumbLineWidth * 6.5f);
        EndDotBGPaint.setStyle(Paint.Style.STROKE);
        EndDotBGPaint.setStrokeJoin(Paint.Join.ROUND);
        EndDotBGPaint.setStrokeCap(Paint.Cap.ROUND);

        // Calculate the drawing scale
        double Mid_Latitude = (MaxLatitude + MinLatitude) / 2;
        double Angle_From_Equator = Math.abs(Mid_Latitude);

        Distance_Proportion = Math.cos(Math.toRadians(Angle_From_Equator));

        DrawScale = Math.max(MaxLatitude - MinLatitude, Distance_Proportion * (MaxLongitude - MinLongitude));
        Lat_Offset = Size_Minus_Margins * (1 - (MaxLatitude - MinLatitude) / DrawScale) / 2;
        Lon_Offset = Size_Minus_Margins * (1 - (Distance_Proportion * (MaxLongitude - MinLongitude) / DrawScale)) / 2;

        Path path = new Path();

        Bitmap ThumbBitmap = Bitmap.createBitmap(Size, Size, Bitmap.Config.ARGB_8888);
        Canvas ThumbCanvas = new Canvas(ThumbBitmap);

        float x = (float) (Lon_Offset + Margin + Size_Minus_Margins * ((latLngList.get(0).Longitude - MinLongitude) * Distance_Proportion / DrawScale));
        float y = (float) (-Lat_Offset + Size - (Margin + Size_Minus_Margins * ((latLngList.get(0).Latitude - MinLatitude) / DrawScale)));
        path.moveTo(x, y);
        for (int i = 1; i < latLngList.size(); i++) {
            x = (float) (Lon_Offset + Margin + Size_Minus_Margins * ((latLngList.get(i).Longitude - MinLongitude) * Distance_Proportion / DrawScale));
            y = (float) (-Lat_Offset + Size - (Margin + Size_Minus_Margins * ((latLngList.get(i).Latitude - MinLatitude) / DrawScale)));

            path.lineTo(x, y);
        }
        //ThumbCanvas.drawPath(path, BGPaint);
        ThumbCanvas.drawPoint(x, y, EndDotBGPaint);
        ThumbCanvas.drawPath(path, drawPaint);
        ThumbCanvas.drawPoint(x, y, EndDotdrawPaint);

        bitmap = ThumbBitmap;
        invalidate();
    }

}
