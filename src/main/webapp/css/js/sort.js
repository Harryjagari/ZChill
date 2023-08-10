jQuery(document).ready(function( $ ) {
    sortPrice = function(){
    $('.product-img').each(function(){
        var data1 = $(this).find(".sortprice").text();
        $(this).attr('data-name', data1);
    
    });
    
    var $products = $('.product-item'),
        $singleProduct = $('.product-img');
    
    $singleProduct.sort(function(a,b){
            var an = a.getAttribute('data-name');
            var bn = b.getAttribute('data-name');
            var numberA = Number(an.replace(/[^0-9\.]+/g,""));
            var numberB = Number(bn.replace(/[^0-9\.]+/g,""));
            an = numberA;
            bn = numberB;
    
            if(an > bn) {
            return 1;
        }
        if(an < bn) {
            return -1;
        }
        return 0;
    });
    
    $singleProduct.detach().appendTo($products);
    
    };
    
    
    sortA = function(){
    $('.product-img').each(function(){
        var data1 = $(this).find(".sort1").text();
        $(this).attr('data-name', data1);
    
    });
    
    var $products = $('.product-item'),
        $singleProduct = $('.product-img');
    
    $singleProduct.sort(function(a,b){
            var an = a.getAttribute('data-name');
            var bn = b.getAttribute('data-name');
    
            if(an > bn) {
            return 1;
        }
        if(an < bn) {
            return -1;
        }
        return 0;
    });
    
    $singleProduct.detach().appendTo($products);
    
    };
    
    sortZ = function(){
    $('.product-img').each(function(){
        var data1 = $(this).find(".sort1").text();
        $(this).attr('data-name', data1);
    
    });
    
    var $products = $('.product-item'),
        $singleProduct = $('.product-img');
    
    $singleProduct.sort(function(a,b){
            var an = a.getAttribute('data-name');
            var bn = b.getAttribute('data-name');
    
            if(an > bn) {
            return -1;
        }
        if(an < bn) {
            return 1;
        }
        return 0;
    });
    
    $singleProduct.detach().appendTo($products);
    
    };
    selectionChange = function(){
    var selectResult = $("#sorting").val();
    
    if(selectResult == "Sort By Price"){
        sortPrice();
    
    } else if(selectResult == "Sort By A-Z"){
        sortA();
    
    } else if(selectResult == "Sort By Z-A"){
        sortZ();
    
    }
    };
    });