<#-- @ftlvariable name="" type="com.enterprisesolutions.views.SuccessView" -->
<#import "base.ftl" as layout />

<@layout.template>
<h2 class="text-center">Congratulations on your purchase!</h2>

<div class="row">
    <div class="col-md-4 col-md-offset-4 product-image">
        <img class="center-block" src="/assets/images/${product}.jpg" />
    </div>
</div>

<div class="text-center">
    You are now renting a ${product.description} for £${product.price} per month.  The first payment will be taken on ${firstPaymentDate}.
</div>
</@layout.template>
