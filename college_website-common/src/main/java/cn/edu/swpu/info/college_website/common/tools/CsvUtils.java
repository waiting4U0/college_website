package cn.edu.swpu.info.college_website.common.tools;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * <b>描述：</b> <br/>
 *
 * @author <b> 作者：</b> cdzhangjunhao@jd.com<br/>
 * <b>时间：</b>2017/09/27 15:43<br/>
 * <b>Copyright (c)</b> 2015-2017京东智能-版权所有<br/>
 */
public class CsvUtils {
    private static final char SEPARATOR = ',';

    public static void writeLine(Writer writer, List<String> values) throws IOException {
        boolean first = true;

        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            if (!first) {
                sb.append(SEPARATOR);
            }
            if (value != null) {
                sb.append(followCsvFormat(value));
            }
            first = false;
        }
        sb.append("\n");
        writer.append(sb.toString());
    }

    //https://tools.ietf.org/html/rfc4180
    private static String followCsvFormat(String value) {
        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;
    }

}

