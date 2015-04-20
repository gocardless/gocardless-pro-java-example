<#-- @ftlvariable name="" type="com.enterprisesolutions.views.ErrorView" -->
<#import "base.ftl" as layout />

<@layout.template>
<h2>Error: ${message} (code ${code})</h2>

See <a href="${documentationUrl}">here</a> for more details.

<#if errors??>
    <h3>Error details:</h3>
    <ul>
        <#list errors as error>
            <li>
                ${error.message}
                <ul>
                    <#if error.reason??><li>Reason: ${error.reason}</li></#if>
                    <#if error.field??><li>Field: ${error.field}</li></#if>
                </ul>
            </li>
        </#list>
    </ul>
</#if>
</@layout.template>
