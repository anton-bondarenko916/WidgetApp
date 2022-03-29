package com.eligon.widget

import android.app.AlarmManager
import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.SystemClock
import android.widget.RemoteViews
import android.widget.TextView
import com.eligon.widget.repository.Repository
import com.eligon.widget.utils.Constants
import java.util.*

class MyWidget : AppWidgetProvider() {

    private lateinit var mSharedPreferences: SharedPreferences

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetIds)
        }
    }

    private fun updateAppWidget(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ){
        mSharedPreferences = context.getSharedPreferences(Constants.APP_PREFERENCES, Context.MODE_PRIVATE)
        val intent = Intent(context, MainActivity::class.java)
        intent.action = AppWidgetManager.ACTION_APPWIDGET_CONFIGURE
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)
        val views = RemoteViews(context.packageName, R.layout.my_widget)
        views.setOnClickPendingIntent(R.id.tvText, pendingIntent)

        val date = Date(mSharedPreferences.getLong(Constants.DATE, 1000))
        val str = "Date: ${date}\n" +
                "Gold: ${mSharedPreferences.getString(Constants.GOLD, "Null")}$"
        views.setTextViewText(R.id.tvText, str)
        appWidgetManager.updateAppWidget(appWidgetIds, views)

    }
}
