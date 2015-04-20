<#-- @ftlvariable name="" type="com.enterprisesolutions.views.HomeView" -->
<#import "base.ftl" as layout />

<@layout.template>
<div class="row">
    <#list products as product>
        <div class="col-md-4">
            <h2 class="text-center">${product.description}</h2>
            <div class="product-image"><img src="/assets/images/${product}.jpg" /></div>
            <a class="btn btn-default btn-lg btn-block" href="/subscribe?product=${product}">Subscribe for £#{product.price} per month</a>
        </div>
    </#list>
</div>
</@layout.template>
