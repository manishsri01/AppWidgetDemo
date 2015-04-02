package com.androidhub4you.app.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyProvider extends AppWidgetProvider {

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {

		// Get all component 
		ComponentName thisWidget = new ComponentName(context, MyProvider.class);
		int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		for (int widgetId : allWidgetIds) {

			//set the remote view to widget
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
					R.layout.activity_my_widget);
			// Set the text to textview you change it in runtime
			remoteViews.setTextViewText(R.id.my_text, "Manish");

			// Intent to set dynamic vaalues to views
			Intent intent = new Intent(context, MyProvider.class);

			intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
			intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

			PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
					0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
			remoteViews.setOnClickPendingIntent(R.id.my_text, pendingIntent);
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
		}
	}
}