package com.sqzr.ccbbs.core.spring.editor;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * sql timestamp 的转换器,用于spring自动赋值到model的时候 用于将前台传来的date字符串转为时间戳的方法
 * Created by weiyang on 2015/6/11.
 */
public class SqlTimestampPropertyEditor extends PropertyEditorSupport {
    public static final String DEFAULT_BATCH_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat sdf;


    /**
     * 使用默认的yyyy-MM-ddTHH:mm方法解析date
     */
    public SqlTimestampPropertyEditor() {
        this.sdf = new SimpleDateFormat(SqlTimestampPropertyEditor.DEFAULT_BATCH_PATTERN);
    }

    /**
     * 使用自定义的pattern来解析时间, see {@link SimpleDateFormat}
     *
     * @param pattern
     * @see SimpleDateFormat#SimpleDateFormat(String)
     */
    public SqlTimestampPropertyEditor(String pattern) {
        this.sdf = new SimpleDateFormat(pattern);
    }

    @Override
    public String getAsText() {
        Timestamp value = (Timestamp) getValue();
        return (value != null ? this.sdf.format(value) : "");
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(new Timestamp(this.sdf.parse(text).getTime()));
        } catch (ParseException ex) {
            setValue(null);
//            throw new IllegalArgumentException("转换失败: " + ex.getMessage(), ex);
        }
    }
}
