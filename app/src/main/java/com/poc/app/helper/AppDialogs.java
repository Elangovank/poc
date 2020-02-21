package com.poc.app.helper;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.poc.app.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Mathan on 08-09-2018.
 */

public class AppDialogs {
    private static Snackbar snackbar;
    private static ProgressDialog progress;

    public static void showProgressDialog(Context context) {
        showProgressDialog(context, "Please wait...");
    }

    /**
     * Title will be app name
     *
     * @param context Context
     * @param desc    String
     */
    public static void showProgressDialog(Context context, String desc) {
        showProgressDialog(context, context.getString(R.string.app_name), desc);
    }

    /**
     * @param context Context
     * @param title   String
     * @param desc    String
     */
    public static void showProgressDialog(Context context, String title, String desc) {
        hideProgressDialog();
        progress = ProgressDialog.show(context, title, desc, true);
        progress.setCancelable(true);
    }
    /**
     * Show Snack Bar Message
     *
     * @param view View
     * @param desc String
     */
    public static void showSnackbarAction(Context aContext, View view, String desc, final OkListener listener) {
        snackbar = Snackbar.make(view, desc, Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (listener != null) {
                            listener.postiveAction();
                        }
                    }
                })
                .setActionTextColor(aContext.getResources().getColor(R.color.skyra_blue));
        snackbar.show();
    }


    public static void hideProgressDialog() {
        if (progress != null)
            progress.dismiss();
    }
    public interface OkListener {
        void postiveAction();
    }

    /**
     * Shows the soft keyboard
     *
     * @param activity Activity
     */
    public static void showSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }


    /**
     * Hides the soft keyboard
     *
     * @param activity Activity
     */
    public static void hideSoftKeyboard(Activity activity, View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    /**
     * Show Toast Message
     *
     * @param context Context
     * @param desc    String
     */
    public static void showToastDialog(Context context, String desc) {
        Toast.makeText(context, desc, Toast.LENGTH_SHORT).show();
    }


    /**
     * Show Snack Bar Message
     *
     * @param view View
     * @param desc String
     */
    public static void showSnackbar(View view, String desc) {
        Snackbar snackbar = Snackbar.make(view, desc, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }


    /**
     * Confirm actions that are critical before proceeding
     *
     * @param context Context
     * @param text    String
     */
    public static void okAction(Context context, String text) {
        okAction(context, context.getResources().getString(R.string.app_name), text);
    }

    /**
     * Confirm actions that are critical before proceeding
     *
     * @param context Context
     * @param text    String
     */
    public static void okAction(Context context, String title, String text) {
        final AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(text);

        builder.setPositiveButton(context.getResources().getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }

    /**
     * Confirm actions that are critical before proceeding
     *
     * @param context  Context
     * @param text     String
     * @param listener ConfirmListener - AppDialogs.ConfirmListener confirmListener = new AppDialogs.ConfirmListener()
     */
    public static void confirmAction(Context context, String text, final ConfirmListener listener) {
        confirmAction(context, context.getResources().getString(R.string.app_name), text, context.getResources().getString(android.R.string.yes), context.getResources().getString(android.R.string.no), listener);
    }

    /**
     * @param context      Context
     * @param text         String
     * @param listener     ConfirmListener - AppDialogs.ConfirmListener confirmListener = new AppDialogs.ConfirmListener()
     * @param postiveText  String
     * @param NegativeText String
     */
    public static void confirmAction(Context context, String title, String text, String postiveText, String NegativeText, final ConfirmListener listener) {
        final AlertDialog alertDialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(text);

        builder.setPositiveButton(postiveText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.postiveAction();
                }
                dialog.dismiss();
            }
        });

        builder.setNegativeButton(NegativeText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (listener != null) {
                    listener.negativeAction();
                }
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                dialogInterface.dismiss();
            }
        });
        alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();
    }


    /**
     * @param context    Context
     * @param datePicker DatePickerDialog - DatePickerDialog.OnDateSetListener from_datePicker = new DatePickerDialog.OnDateSetListener()
     */

    public static void showDateDialog(Context context, DatePickerDialog.OnDateSetListener datePicker) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(context, datePicker, year, month, dayOfMonth);
        dialog.setCancelable(false);
        dialog.getDatePicker().setMaxDate(new Date().getTime());

        dialog.setTitle("");
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "SELECT", dialog);
        dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "CANCEL", dialog);
        dialog.show();
    }

    /**
     * @param context         Context
     * @param SelectedDate    String (dd-MM-yyyy)
     * @param from_datePicker DatePickerDialog - DatePickerDialog.OnDateSetListener from_datePicker = new DatePickerDialog.OnDateSetListener()
     */

    public static void showFromDateDialog(Context context, String SelectedDate, DatePickerDialog.OnDateSetListener from_datePicker) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String date[] = SelectedDate.split("-");

        DatePickerDialog dialog = new DatePickerDialog(context, from_datePicker, year, month, dayOfMonth);
        dialog.setCancelable(false);
        dialog.getDatePicker().setMaxDate(new Date().getTime());

        // Selected Date
        dialog.getDatePicker().init(Integer.parseInt(date[2]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[0]), null);

        dialog.setTitle("");
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "SELECT", dialog);
        dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "CANCEL", dialog);
        dialog.show();
    }

    /**
     * @param context       Context
     * @param format        SimpleDateFormat
     * @param fromDate      String (dd-MM-yyyy)
     * @param SelectedDate  String (dd-MM-yyyy)
     * @param to_datePicker DatePickerDialog - DatePickerDialog.OnDateSetListener from_datePicker = new DatePickerDialog.OnDateSetListener()
     */

    public static void showToDateDialog(Context context, String format, String fromDate, String SelectedDate, DatePickerDialog.OnDateSetListener to_datePicker) {
        SimpleDateFormat sdf_date = new SimpleDateFormat(format, Locale.ENGLISH);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        String Selected[] = SelectedDate.split("-");
        try {
            DatePickerDialog dialog = new DatePickerDialog(context, to_datePicker, year, month, dayOfMonth);
            dialog.setCancelable(false);

            // Selected Date
            dialog.getDatePicker().init(Integer.parseInt(Selected[2]), Integer.parseInt(Selected[1]) - 1, Integer.parseInt(Selected[0]), null);

            dialog.getDatePicker().setMinDate(sdf_date.parse(fromDate).getTime());
            dialog.getDatePicker().setMaxDate(new Date().getTime());

            dialog.setTitle("");
            dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "SELECT", dialog);
            dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "CANCEL", dialog);
            dialog.show();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param context  Context
     * @param listener TimePickerDialog.OnTimeSetListener
     */

    public static void showTimeDialog(Context context, TimePickerDialog.OnTimeSetListener listener) {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(context, listener, hourOfDay, minute, false); // True - 24hours format
        dialog.setCancelable(false);
        dialog.setTitle("");
        dialog.setButton(DatePickerDialog.BUTTON_POSITIVE, "SELECT", dialog);
        dialog.setButton(DatePickerDialog.BUTTON_NEGATIVE, "CANCEL", dialog);
        dialog.show();
    }


    /**
     * @param context Context
     * @param aType   String
     */

    public static void showShareDialog(Context context, String aType) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.setType(aType);
        context.startActivity(Intent.createChooser(sendIntent, "Choose Your Option"));
    }

    /**
     * AppDialogs.ConfirmListener confirmListener = new AppDialogs.ConfirmListener()
     * Simple interface can be implemented for confirm an action via dialogs
     */
    public interface ConfirmListener {
        void postiveAction();

        void negativeAction();
    }
}
