package mountainq.helloegg.tiptourguide;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import mountainq.helloegg.tiptourguide.activities.SActivity;
import mountainq.helloegg.tiptourguide.manager.PropertyManager;

/**
 * Created by dnay2 on 2016-11-26.
 */

public class ProfileSettingActivity extends SActivity {

    PropertyManager sharedPregerences = PropertyManager.getInstance();

    Button btn_notice;
    Button btn_customcenter;
    Button btn_termcond;
    Button btn_personalinfo;

    Context context;

    Switch switchPush;
    Switch switchAutoLogin;

    View.OnClickListener bHandler = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_notice:
                    showNotice();
                    break;
                case R.id.btn_customcenter:
                    showCustomcenter();
                    break;
                case R.id.btn_termcond:
                    showTermcond();
                    break;
                case R.id.btn_personalinfo:
                    showPersonalInfo();
                    break;
            }
        }
    };
    /**
     * 스위치 리스너
     */
    CompoundButton.OnCheckedChangeListener switchListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()){
                case R.id.switch_push:
                    if (isChecked) {
                        sharedPregerences.setPushOk(1);
                        Toast.makeText(ProfileSettingActivity.this, "푸쉬알람이 켜졌습니다!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileSettingActivity.this, "푸쉬알림이 꺼졌습니다!", Toast.LENGTH_SHORT).show();
                        sharedPregerences.setPushOk(-1);
                    }
                    break;
                case R.id.switch_autologin:
                    if (isChecked) {
                        sharedPregerences.setAutoLogin(1);
                        Toast.makeText(ProfileSettingActivity.this, "자동로그인이 설정 되었습니다!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ProfileSettingActivity.this, "자동로그아웃이 설정 되었습니다!", Toast.LENGTH_SHORT).show();
                        sharedPregerences.setAutoLogin(-1);
                        Intent intent = new Intent(context, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    break;
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_profilesetting);
        Log.d("Test", "fragment created");

        context = this;
        setToolbar(getIntent().getStringExtra("text"));
        init();
    }

    private void init(){
        btn_notice = (Button) findViewById(R.id.btn_notice);
        btn_customcenter = (Button) findViewById(R.id.btn_customcenter);
        btn_termcond = (Button) findViewById(R.id.btn_termcond);
        btn_personalinfo = (Button) findViewById(R.id.btn_personalinfo);
        switchPush = (Switch) findViewById(R.id.switch_push);
        switchAutoLogin = (Switch) findViewById(R.id.switch_autologin);

        if(sharedPregerences.getAutoLogin() != -1){
            switchAutoLogin.setChecked(true);
        } else{
            switchAutoLogin.setChecked(false);
        }

        if(sharedPregerences.getPushOk() != -1){
            switchPush.setChecked(true);
        }else {
            switchPush.setChecked(false);
        }

        switchAutoLogin.setOnCheckedChangeListener(switchListener);
        switchPush.setOnCheckedChangeListener(switchListener);

        btn_notice.setOnClickListener(bHandler);
        btn_customcenter.setOnClickListener(bHandler);
        btn_termcond.setOnClickListener(bHandler);
        btn_personalinfo.setOnClickListener(bHandler);
    }




    /**
     * 공지사항
     */
    private void showNotice(){
        AlertDialog.Builder noticeBuilder = new AlertDialog.Builder(this);
        noticeBuilder.setTitle("공지");
        noticeBuilder.setMessage("공지 공사중 입니다!");
        noticeBuilder.setCancelable(true);
        noticeBuilder.setPositiveButton("확인", new ProfileSettingActivity.CancelOnClickListener()).show();
    }

    /**
     * 고객센터
     */
    private void showCustomcenter(){
        AlertDialog.Builder customBuilder = new AlertDialog.Builder(this);
        customBuilder.setTitle("사용안내");
        customBuilder.setMessage("팁투어 사용안내\n" +
                "\n" +
                "외국인들이 한국에서 가이드를 손쉽게 구할수 있도록, 아마추어 가이드들이 자신의 외국어 실력과 지식을 활용하여 가이드 일을 할수 있게 도와주기 위하여 팁투어를 제작하였습니다.\n" +
                "\n" +
                "1. 가이드를 구할때는 이렇게!\n" +
                "주변에 있는 가이드들의 별점, 설명을 보고 자신에게 가장 알맞는 가이드를 선택하세요!\n" +
                "\n" +
                "2. 가이드 등록은 이렇게!\n" +
                "최대한 솔직하게 자신을 어필할수 있는 글을 작성해 보세요! 부적합한 설명과 광고 등 팁투어와 무관한 내용을 게시할 경우 팁투어 운영원칙에 의해 회원 삭제 및 이용제한 조치를 받으실 수 있습니다.\n" +
                "\n" +
                "3. 가이드 할때는 이렇게!\n" +
                "가이드를 하겠다고 등록을 함과 동시에 회원님의 별점, 이름 그리고 설명은 모든 이에게 공개가 됩니다. 관광회원이 회원님을 가이드로 고르면 푸쉬 알람이 전달되며 수락 혹은 거절을 할 수 있습니다\n" +
                "\n" +
                "4. 부적절한 회원을 보았다면?\n" +
                "팁투어 운영원칙에 어긋나는 부적절한 회원을 보셨다면 바로 신고해주세요!\n" +
                "부적절한 회원에 대한 내용은 이용약관에서 확인할 수 있습니다.\n");
        customBuilder.setCancelable(true);
        customBuilder.setPositiveButton("확인", new ProfileSettingActivity.CancelOnClickListener()).show();
    }

    /**
     * 이용약관
     */
    private void showTermcond(){
        AlertDialog.Builder termcondBuilder = new AlertDialog.Builder(this);
        termcondBuilder.setTitle("이용약관");
        termcondBuilder.setMessage("제 1조 이 약관은 팁투어팀(이하 \"회사\" 또는 “팁투어”)이 제공하는 팁투어, 팁투어 어플리케이션(Android) 등 팁투어팀이 제공하는 서비스를 이용함에 있어 회사와 회원 간의 권리, 의무 및 책임사항 등을 규정함을 목적으로 합니다." +
                "1. \"회원\"이란 회사가 제공하는 서비스에 접속하여 본 약관을 동의하고 가입한 후 서비스를 제공받는 자를 말합니다.\n" +
                "2. \"서비스\"라 함은 구현되는 단말기(PC, TV, 휴대형단말기 등의 각종 유무선 장치를 포함)와 상관없이 \"회원\"이 이용할 수 있는 내전공 관련 제반 서비스를 의미합니다.\n" +
                "3. \"아이디(ID)\"라 함은 \"회원\"의 식별과 \"서비스\" 이용을 위하여 \"회원\"이 정하고 \"회사\"가 승인하는 이메일을 의미합니다.\n" +
                "4. \"비밀번호\"라 함은 \"회원\"이 부여 받은 \"아이디”와 일치되는 \"회원\"임을 확인하고 비밀보호를 위해 \"회원\" 자신이 정한 문자 또는 숫자의 조합을 의미합니다.\n" +
                "5. \"유료서비스\"라 함은 \"회사\"가 유료로 제공하는 각종 온라인디지털콘텐츠(각종 정보콘텐츠, 프리미엄 서비스 등) 및 제반 서비스를 의미합니다.\n" +
                "6. \"전공 마일리지\"라 함은 서비스의 효율적 이용을 위해 회사가 임의로 책정 또는 지급, 조정할 수 있는 재산적 가치가 없는 \"서비스\" 상의 가상 데이터를 의미합니다.\n" +
                "7. \"게시물\"이라 함은 \"회원\"이 \"서비스\"를 이용함에 있어 \"서비스상\"에 게시한 부호ㆍ문자ㆍ음성ㆍ음향ㆍ화상ㆍ동영상 등의 정보 형태의 글, 사진, 동영상 및 각종 파일과 링크 등을 의미합니다.\n" +
                "제 3조(약관의 게시와 개정)\n" +
                "1. \"회사\"는 \"약관의규제에관한법률\", \"정보통신망이용촉진및정보보호등에관한법률(이하 \"정보통신망법\")\" 등 관련법을 위배하지 않는 범위에서 본 약관을 개정할 수 있습니다.\n" +
                "2. 만약 개정내용이 회원에게 불리한 경우에는 적용일자 30일 이전부터 적용일자 전일까지 서비스 초기 화면에 공지합니다.\n" +
                "3. 회사가 제 2항에 따라 개정 약관을 공지한 후 회원이 30일 내에 명시적으로 거부 의사 표시를 하지 않은 경우 회원이 개정 약관에 동의한 것으로 간주합니다.\n" +
                "4. 회원이 개정약관의 적용에 동의하지 않는 경우 회사는 개정 약관의 내용을 해당 회원에게 적용할 수 없으며, 이 경우 회원은 이용계약을 해지할 수 있습니다. 다만 기존 약관을 부분적으로 적용할 수 없는 경우 회사는 이용계약을 해지할 수 있습니다\n");
        termcondBuilder.setCancelable(true);
        termcondBuilder.setPositiveButton("확인", new ProfileSettingActivity.CancelOnClickListener()).show();
    }

    /**
     * 개발정보
     */
    private void showPersonalInfo(){
        AlertDialog.Builder personalBuilder = new AlertDialog.Builder(this);
        personalBuilder.setTitle("개발자 정보");
        personalBuilder.setMessage("팁투어 개발자 정보문\n" +
                "팁투어는 동국대학교 생명과학과 10학번 임규산과 성균관대학교 컴퓨터공학과 15학번 강철민이 제작하였습니다.\n" +
                "\n" +
                "1. 여행을 사랑하는 두명의 개발자가 한국에 방문하는 관광객들이 좋은 추억을 만들어갔으면 좋겠다는 취지에서 시작하였습니다.\n" +
                "한국을 사랑하고 찾는 관광객들이 많아졌으면 좋겠습니다.\n" +
                "\n");
        personalBuilder.setCancelable(true);
        personalBuilder.setPositiveButton("확인", new ProfileSettingActivity.CancelOnClickListener()).show();
    }




    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(ProfileSettingActivity.this, "닫았습니다", Toast.LENGTH_SHORT).show();
        }
    }

}
