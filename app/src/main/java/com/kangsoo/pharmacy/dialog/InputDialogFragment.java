package com.kangsoo.pharmacy.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.kangsoo.pharmacy.R;
import com.kontakt.sdk.core.interfaces.BiConsumer;

public class InputDialogFragment extends DialogFragment {

    protected static final String TITLE_TAG = "title of a dialog";
    protected static final String BUTTON_TEXT_TAG = "text of a button";
    protected static final String MESSAGE_TAG = "main message";

    protected  View view;
    protected  EditText inputText;
    protected Button submitButton;
    protected Drawable icon;
    protected BiConsumer<DialogInterface, String> biConsumer;

    public static InputDialogFragment newInstance(final String title,
                                                  final String message,
                                                  final String buttonText,
                                                  final BiConsumer<DialogInterface, String> submitBiConsumer) {
        return newInstance(title, message, buttonText, null, submitBiConsumer);
    }

    public static InputDialogFragment newInstance(final String title,
                                                  final String message,
                                                  final String buttonText,
                                                  final Drawable icon,
                                                  final BiConsumer<DialogInterface, String> submitBiConsumer) {
        InputDialogFragment dialog = new InputDialogFragment();
        Bundle args = new Bundle();
        args.putString(TITLE_TAG, title);
        args.putString(BUTTON_TEXT_TAG, buttonText);
        args.putString(MESSAGE_TAG, message);
        dialog.setArguments(args);
        dialog.icon = icon;
        dialog.biConsumer = submitBiConsumer;
        return dialog;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle arguments = getArguments();
        final LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        initViews(inflater.inflate(R.layout.input_dialog_fragment, null));

        return new AlertDialog.Builder(getActivity())
                .setTitle(arguments.getString(TITLE_TAG))
                .setIcon(icon)
                .setView(view)
                .setNegativeButton(getString(R.string.cancel), null)
                .setPositiveButton(arguments.getString(BUTTON_TEXT_TAG), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String text = inputText.getText().toString().trim();
                        if (biConsumer!= null) {
                            inputText.setText("");
                            biConsumer.accept(dialog, text);
                        }
                    }
                }).create();
    }

    protected Button getSubmitButton() {
        if(submitButton == null) {
            submitButton = ((AlertDialog)getDialog()).getButton(DialogInterface.BUTTON_POSITIVE);
        }

        return submitButton;
    }

    private void initViews(final View mainView) {
        view = mainView;
        inputText = (EditText) view.findViewById(R.id.input);
        ((TextView)view.findViewById(R.id.message)).setText(getArguments().getString(MESSAGE_TAG));
    }
}
