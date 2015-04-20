<#import "base.ftl" as layout />

<@layout.template>
<div class="row">
    <div class="col-md-12">
        <h2>Error</h2>
    </div>

    <ul>
        <#list errors as error>
            <li>${error.field}: ${error.message}</li>
        </#list>
    </ul>
</div>
</@layout.template>
