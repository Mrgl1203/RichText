package com.gl.richtext;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView richText;

    /**
     * Spannable.SPAN_EXCLUSIVE_EXCLUSIVE：前后都不包括，即在指定范围的前面和后面插入新字符都不会应用新样式
     * Spannable.SPAN_EXCLUSIVE_INCLUSIVE	：前面不包括，后面包括。即仅在范围字符的后面插入新字符时会应用新样式
     * Spannable.SPAN_INCLUSIVE_EXCLUSIVE	：前面包括，后面不包括。
     * Spannable.SPAN_INCLUSIVE_INCLUSIVE	：前后都包括
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        richText = (TextView) findViewById(R.id.richText);
        //拼接字符串
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();

        final String nickname = "大家好我的名字叫顾磊:";
        //如果不想又太长的名字显示可以给名字限制长度
        TextPaint textPaint = new TextPaint(new Paint());
        String shortNickname = TextUtils.ellipsize(nickname, textPaint, 100, TextUtils.TruncateAt.END).toString();
        SpannableString spannableNickname = new SpannableString(shortNickname);

        //名字字符串进行圆角边框修饰
        RadiusBackgroundSpan backgroundSpan = new RadiusBackgroundSpan(getResources().getColor(R.color.cbg), 10);
        //设置圆角边框只包裹住名字部分
        spannableNickname.setSpan(backgroundSpan, 0, spannableNickname.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置名字部分字段的点击事件
        spannableNickname.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                Toast.makeText(MainActivity.this, nickname, Toast.LENGTH_SHORT).show();
            }
        }, 0, spannableNickname.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //这行代码很关键，表示文本被设置成链接点击事件哦
        richText.setMovementMethod(LinkMovementMethod.getInstance());

        String content = "新浪娱乐讯  据台湾媒体报道，某知名网游在9月3日举办六周年明星表演赛，" +
                "由周杰伦与吴亦凡各自领队进行一场对决。" +
                "喜爱玩打野的周杰伦今选择非正规的打野角色“盖伦”，虽然前期没什么发挥，" +
                "且队伍初期面临极大劣势，但在前职业选手卢本伟（55开）的寇格魔（大嘴）Carry下大逆转翻盘！";

        SpannableString spannableContent = new SpannableString(content);
        //设置内容颜色样式
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(getResources().getColor(R.color.cContent));
        spannableContent.setSpan(colorSpan, 0, spannableContent.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //最后进行拼接
        stringBuilder.append(spannableNickname).append(" ").append(spannableContent);
        //别忘了settext哦
        richText.setText(stringBuilder);
    }
}
