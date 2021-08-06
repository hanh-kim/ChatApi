package com.hanhpk.chatapi.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.hanhpk.chatapi.R;
import com.hanhpk.chatapi.adapter.ChatAdapter;
import com.hanhpk.chatapi.model.ChatFactory;
import com.hanhpk.chatapi.model.ChatType;
import com.hanhpk.chatapi.model.Result;
import com.hanhpk.chatapi.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    Toolbar toolbar;
    RecyclerView rcvMessage;
    ImageView icInfo, icSend, icState, imgAvatar;
    TextView tvName, tvState;
    EditText edtMessage;
    ChatAdapter adapter;
    List<Result> resultList;
    private List<ChatFactory> factoryList;
    private Repository repository;
    private CompositeDisposable compositeDisposable;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initUi();
        initData();
        icSend.setOnClickListener(this::onClick);
        edtMessage.setOnTouchListener((view, motionEvent) -> {
            if (factoryList.size() > 0) {
                rcvMessage.scrollToPosition(factoryList.size() - 1);
            }
            return false;
        });

    }

    private void initUi() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rcvMessage = findViewById(R.id.rcvMessage);
        icInfo = toolbar.findViewById(R.id.icInfo);
        imgAvatar = toolbar.findViewById(R.id.imgAvatar);
        tvState = toolbar.findViewById(R.id.tvState);
        tvName = toolbar.findViewById(R.id.tvName);
        icState = toolbar.findViewById(R.id.icDotActive);
        icSend = findViewById(R.id.icSend);
        edtMessage = findViewById(R.id.edtMessage);
    }

    private void initData() {
        resultList = new ArrayList<>();
        factoryList = new ArrayList<>();
        adapter = new ChatAdapter();
        adapter.setData(factoryList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvMessage.setLayoutManager(layoutManager);
        rcvMessage.setAdapter(adapter);
        repository = new Repository();
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void onClick(View v) {
        if (icSend.equals(v)) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String mMessage = edtMessage.getText().toString().trim();
        if (TextUtils.isEmpty(mMessage)) {
            return;
        }
        rcvMessage.scrollToPosition(factoryList.size() - 1);
        factoryList.add(new ChatFactory(ChatType.TYPE_MY_MESSAGE, mMessage));
        //  adapter.notifyDataSetChanged();
        clearText();
        respondMessage(mMessage);

    }

    private void respondMessage(String message) {
        resetState(true);// setup state true
        compositeDisposable.add(
                repository.getRespondMessage(message)
                        .subscribe(
                                result -> {
                                    factoryList.add(new ChatFactory(ChatType.TYPE_RESPOND_MESSAGE, result));
                                    rcvMessage.scrollToPosition(factoryList.size() - 1);
                                },
                                error -> {
                                    Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));

    }

    private void resetState(Boolean isActive) {
        icState.setVisibility(View.VISIBLE);
        tvState.setText("Đang hoạt động");
        tvState.setTextColor(Color.parseColor("#17FF00"));
    }

    private void clearText() {
        hideSoftKeyBoard();
        edtMessage.clearFocus();
        edtMessage.setText("");

    }

    private void hideSoftKeyBoard() {
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }


//    @SuppressLint("IntentReset")
//    private void takePhoto() {
//        Intent intentTakePhoto = new Intent();
//        intentTakePhoto.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
//        getIntent.setType("image/*");
//
//        Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        pickPhoto.setType("image/*");
//
//
//        Intent chooserIntent = Intent.createChooser(getIntent, "Select Image");
//        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{intentTakePhoto, pickPhoto});
//
//        if (chooserIntent.resolveActivity(getPackageManager())!=null) {
//            startActivityForResult(chooserIntent, REQUEST_TAKE_PHOTO);
//        }
//
//
//    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}