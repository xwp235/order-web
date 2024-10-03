package com.xweb.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.VisitableElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyBatisPlugin extends PluginAdapter  {

    private static final String CREATED_AT = "createdAt";
    private static final String UPDATED_AT = "updatedAt";
    private static final String CREATED_AT_PLACEHOLDER ="#{createdAt,jdbcType=TIMESTAMP}";
    private static final String UPDATED_AT_PLACEHOLDER ="#{updatedAt,jdbcType=TIMESTAMP}";
    private static final String UPDATED_BY_EXAMPLE_PLACEHOLDER = "#{row.updatedAt,jdbcType=TIMESTAMP}";
    private static final String CURRENT_TIMESTAMP = "CURRENT_TIMESTAMP";
    private static final String COLUMN_CREATED_AT = "created_at";
    private static final String COLUMN_UPDATED_AT = "updated_at";
    private static final char COMMA = ',';

    /**
     * 为实体添加lombok的注解
     *
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        //添加domain的import
//        topLevelClass.addImportedType("lombok.Data");
        /*topLevelClass.addImportedType("lombok.Builder");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");*/

        //添加domain的注解
//        topLevelClass.addAnnotation("@Data");
        /*topLevelClass.addAnnotation("@Builder");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");*/

        //添加domain的注释
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("* Created by Mybatis Generator on " + date2Str(new Date()));
        topLevelClass.addJavaDocLine("*/");

        return true;
    }

    /**
     * 为实体类字段添加注释
     *
     * @param field
     * @param topLevelClass
     * @param introspectedColumn
     * @param introspectedTable
     * @param modelClassType
     * @return
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        field.addJavaDocLine("/**");
        String remarks = introspectedColumn.getRemarks();
        if (StringUtility.stringHasValue(remarks)) {
            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
            for (String remarkLine : remarkLines) {
                field.addJavaDocLine(" * " + remarkLine);
            }
        }
        field.addJavaDocLine(" */");
        if (introspectedColumn.getJdbcType() == Types.DATE) {
            field.setType(new FullyQualifiedJavaType("java.time.LocalDate"));
            topLevelClass.addImportedType("java.time.LocalDate");
        }
        if (introspectedColumn.getJdbcType() == Types.TIME) {
            field.setType(new FullyQualifiedJavaType("java.time.LocalTime"));
            topLevelClass.addImportedType("java.time.LocalTime");
        }
        if (introspectedColumn.getJdbcType() == Types.TIMESTAMP_WITH_TIMEZONE || introspectedColumn.getJdbcType() == Types.TIMESTAMP) {
            field.setType(new FullyQualifiedJavaType("java.time.ZonedDateTime"));
            topLevelClass.addImportedType("java.time.ZonedDateTime");
        }
        if (introspectedColumn.getJdbcType() == Types.TIME_WITH_TIMEZONE) {
            field.setType(new FullyQualifiedJavaType("java.time.OffsetTime"));
            topLevelClass.addImportedType("java.time.OffsetTime");
        }
        return true;

    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        // 获取属性名称
        String propertyName = introspectedColumn.getJavaProperty();
        // 如果属性名称为 createdAt 或 updatedAt，则不生成 getter 方法
        if ("createdAt".equals(propertyName) || "updatedAt".equals(propertyName)) {
            return false;  // 返回 false 表示不生成
        }
        if (introspectedColumn.getJdbcType() == Types.DATE || introspectedColumn.getJdbcType() == Types.TIME || introspectedColumn.getJdbcType() == Types.TIMESTAMP || introspectedColumn.getJdbcType() == Types.TIMESTAMP_WITH_TIMEZONE || introspectedColumn.getJdbcType() == Types.TIME_WITH_TIMEZONE) {
            // 修改参数类型为 ZonedDateTime
            method.getParameters().clear();
            if (introspectedColumn.getJdbcType() == Types.DATE) {
                method.addParameter(new Parameter(new FullyQualifiedJavaType("java.time.LocalDate"), introspectedColumn.getJavaProperty()));
            }
            if (introspectedColumn.getJdbcType() == Types.TIME) {
                method.addParameter(new Parameter(new FullyQualifiedJavaType("java.time.LocalTime"), introspectedColumn.getJavaProperty()));
            }
            if (introspectedColumn.getJdbcType() == Types.TIMESTAMP_WITH_TIMEZONE || introspectedColumn.getJdbcType() == Types.TIMESTAMP) {
                method.addParameter(new Parameter(new FullyQualifiedJavaType("java.time.ZonedDateTime"), introspectedColumn.getJavaProperty()));
            }
            if (introspectedColumn.getJdbcType() == Types.TIME_WITH_TIMEZONE) {
                method.addParameter(new Parameter(new FullyQualifiedJavaType("java.time.OffsetTime"), introspectedColumn.getJavaProperty()));
            }
            // 更新方法体
            method.getBodyLines().clear();
            method.addBodyLine("this." + introspectedColumn.getJavaProperty() + " = " + introspectedColumn.getJavaProperty() + ";");
        }
        // 其他属性保持默认行为
        return super.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        if (introspectedColumn.getJdbcType() == Types.DATE || introspectedColumn.getJdbcType() == Types.TIME || introspectedColumn.getJdbcType() == Types.TIMESTAMP || introspectedColumn.getJdbcType() == Types.TIMESTAMP_WITH_TIMEZONE || introspectedColumn.getJdbcType() == Types.TIME_WITH_TIMEZONE) {
            if (introspectedColumn.getJdbcType() == Types.TIMESTAMP_WITH_TIMEZONE || introspectedColumn.getJdbcType() == Types.TIMESTAMP) {
                method.setReturnType(new FullyQualifiedJavaType("java.time.ZonedDateTime"));
            }
            if (introspectedColumn.getJdbcType() == Types.TIME_WITH_TIMEZONE) {
                method.setReturnType(new FullyQualifiedJavaType("java.time.OffsetTime"));
            }
            if (introspectedColumn.getJdbcType() == Types.TIME) {
                method.setReturnType(new FullyQualifiedJavaType("java.time.LocalTime"));
            }
            if (introspectedColumn.getJdbcType() == Types.DATE) {
                method.setReturnType(new FullyQualifiedJavaType("java.time.LocalDate"));
            }
            // 更新方法体
            method.getBodyLines().clear();
            method.addBodyLine("return " + introspectedColumn.getJavaProperty() + ";");
        }
        return super.modelSetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
    }

    @Override
    public boolean sqlMapInsertElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        var elements = element.getElements();
        var textElementList = new ArrayList<TextElement>();
        for (var visitableElement : elements) {
            var e = (TextElement) visitableElement;
            var sqlContent = e.getContent();
            if (sqlContent.contains(CREATED_AT) || sqlContent.contains(UPDATED_AT)) {
                sqlContent = sqlContent.replace(CREATED_AT_PLACEHOLDER, CURRENT_TIMESTAMP);
                sqlContent = sqlContent.replace(UPDATED_AT_PLACEHOLDER, CURRENT_TIMESTAMP);
                textElementList.add(new TextElement(sqlContent));
            } else {
                textElementList.add(e);
            }
        }
        elements.clear();
        elements.addAll(textElementList);
        return super.sqlMapInsertElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapInsertSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        var elements = element.getElements();
        var newElementList = new ArrayList<VisitableElement>();
        for (var visitableElement : elements) {
            if (visitableElement instanceof XmlElement trimEl) {
                var trimElList = new ArrayList<VisitableElement>();
                var ifEls = trimEl.getElements();
                for (var ifXmlEl : ifEls) {
                    var ifEl = (XmlElement) ifXmlEl;
                    var ifElInnerText = (TextElement)ifEl.getElements().get(0);
                    var ifSqlContent = ifElInnerText.getContent();
                    if (ifSqlContent.equals(COLUMN_CREATED_AT+COMMA)){
                        trimElList.add(new TextElement(COLUMN_CREATED_AT+COMMA));
                    } else if (ifSqlContent.equals(COLUMN_UPDATED_AT+COMMA)){
                        trimElList.add(new TextElement(COLUMN_UPDATED_AT+COMMA));
                    } else if (ifSqlContent.contains(CREATED_AT_PLACEHOLDER) || ifSqlContent.contains(UPDATED_AT_PLACEHOLDER)) {
                        trimElList.add(new TextElement(CURRENT_TIMESTAMP+COMMA));
                    } else {
                        trimElList.add(ifEl);
                    }
                }
                ifEls.clear();
                ifEls.addAll(trimElList);
                newElementList.add(trimEl);
            } else if (visitableElement instanceof TextElement el) {
                newElementList.add(el);
            }
        }
        elements.clear();
        elements.addAll(newElementList);
        return super.sqlMapInsertSelectiveElementGenerated(element, introspectedTable);
    }

    // updateByExampleWithBLOBs
    @Override
    public boolean sqlMapUpdateByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        assembleUpdateByExampleSql(element);
        return super.sqlMapUpdateByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    // updateByExample
    @Override
    public boolean sqlMapUpdateByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        assembleUpdateByExampleSql(element);
        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    // updateByPrimaryKeySelective
    @Override
    public boolean sqlMapUpdateByPrimaryKeySelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        var elements = element.getElements();
        var newElementList = new ArrayList<VisitableElement>();
        for (var visitableElement : elements) {
            if (visitableElement instanceof XmlElement setEl) {
                var ifEls = setEl.getElements();
                var ifElList = new ArrayList<VisitableElement>();
                for (var ifXmlEl : ifEls) {
                    var ifEl = (XmlElement) ifXmlEl;
                    var ifElInnerText = (TextElement)ifEl.getElements().get(0);
                    var ifSqlContent = ifElInnerText.getContent();
                    if (ifSqlContent.contains(CREATED_AT_PLACEHOLDER)) {
                        continue;
                    }
                    if (ifSqlContent.contains(UPDATED_AT_PLACEHOLDER)) {
                        ifElList.add(new TextElement(COLUMN_UPDATED_AT+" = " + CURRENT_TIMESTAMP+COMMA));
                    } else {
                        ifElList.add(ifEl);
                    }
                }
                ifEls.clear();
                ifEls.addAll(ifElList);
                newElementList.add(setEl);
            } else if (visitableElement instanceof TextElement el) {
                newElementList.add(el);
            }
        }
        elements.clear();
        elements.addAll(newElementList);
        return super.sqlMapUpdateByPrimaryKeySelectiveElementGenerated(element, introspectedTable);
    }

    // updateByPrimaryKeyWithBLOBs
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        assembleUpdateByPrimaryKeySql(element);
        return super.sqlMapUpdateByPrimaryKeyWithBLOBsElementGenerated(element, introspectedTable);
    }

    // updateByPrimaryKey
    @Override
    public boolean sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        assembleUpdateByPrimaryKeySql(element);
        return super.sqlMapUpdateByPrimaryKeyWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    // updateByExampleSelective
    @Override
    public boolean sqlMapUpdateByExampleSelectiveElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        var elements = element.getElements();
        var newElementList = new ArrayList<VisitableElement>();
        for (var visitableElement : elements) {
            if (visitableElement instanceof TextElement textEl) {
                newElementList.add(textEl);
            } else if (visitableElement instanceof XmlElement xmlEl) {
                if ("set".equalsIgnoreCase(xmlEl.getName())) {
                    var ifEls = xmlEl.getElements();
                    var ifElList = new ArrayList<VisitableElement>();
                    for (VisitableElement ifEl : ifEls) {
                        var ifXmlEl = (XmlElement) ifEl;
                        var ifElInnerText = (TextElement)ifXmlEl.getElements().get(0);
                        var ifSqlContent = ifElInnerText.getContent();
                        if (ifSqlContent.contains(COLUMN_CREATED_AT)) {
                            continue;
                        }
                        if (ifSqlContent.contains(COLUMN_UPDATED_AT)) {
                            ifElList.add(new TextElement(COLUMN_UPDATED_AT+" = " + CURRENT_TIMESTAMP+COMMA));
                        } else {
                            ifElList.add(ifEl);
                        }
                    }
                    ifEls.clear();
                    ifEls.addAll(ifElList);
                }
                newElementList.add(xmlEl);
            }
        }
        elements.clear();
        elements.addAll(newElementList);
        return super.sqlMapUpdateByExampleSelectiveElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    private void assembleUpdateByExampleSql(XmlElement element) {
        var elements = element.getElements();
        var newElementList = new ArrayList<VisitableElement>();

        for (var visitableElement : elements) {
            if (visitableElement instanceof XmlElement ifEl) {
                newElementList.add(ifEl);
            } else if (visitableElement instanceof TextElement textEl) {
                assembleCurrentTimestampWhenUpdate(textEl, UPDATED_BY_EXAMPLE_PLACEHOLDER, newElementList);
            }
        }

        elements.clear();
        elements.addAll(newElementList);
    }

    private void assembleUpdateByPrimaryKeySql(XmlElement element) {
        var elements = element.getElements();
        var newElementList = new ArrayList<VisitableElement>();
        for (var visitableElement : elements) {
            var textElement = (TextElement) visitableElement;
            assembleCurrentTimestampWhenUpdate(textElement, UPDATED_AT_PLACEHOLDER, newElementList);
        }
        elements.clear();
        elements.addAll(newElementList);
    }

    private void assembleCurrentTimestampWhenUpdate(TextElement textEl, String updatedByExamplePlaceholder, ArrayList<VisitableElement> newElementList) {
        var textSqlContent = textEl.getContent();
        if (textSqlContent.contains(COLUMN_CREATED_AT)) {
            return;
        }
        if (textSqlContent.contains(COLUMN_UPDATED_AT)) {
            var updatedAtSql = textSqlContent.replace(updatedByExamplePlaceholder, CURRENT_TIMESTAMP);
            newElementList.add(new TextElement(updatedAtSql));
        } else {
            newElementList.add(textEl);
        }
    }

    private String date2Str(Date date) {
        var sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sdf.format(date);
    }

}
