window.onload=function(){
    var vue = new Vue({
        el:"#cart_div",
        data:{
            cart:{}
        },
        methods:{
            getCart:function(){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'cartInfo'
                    }
                })
                    .then(function (value) {
                        var cart = value.data ;
                        vue.cart = cart ;
                    })
                    .catch(function (reason) {  });
            },
            editCart:function(cartItemId , buyCount){
                axios({
                    method:"POST",
                    url:"cart.do",
                    params:{
                        operate:'editCart',
                        cartItemId:cartItemId,
                        buyCount:buyCount
                    }
                })
                    .then(function (value) {
                        vue.getCart();
                    })
                    .catch(function (reason) {  });
            }
        },
        mounted:function(){
            this.getCart() ;
        }
    });







}