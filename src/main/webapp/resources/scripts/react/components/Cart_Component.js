

class Cart extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      cartItems: [],
      grandTotal: 0,
      coupon: "",
    };

    this.queryCart = this.queryCart.bind(this);
    this.handleCartItemRemoval = this.handleCartItemRemoval.bind(this);
    this.handleCartItemQuantityChange = this.handleCartItemQuantityChange.bind(this);
    this.handleCouponChange = this.handleCouponChange.bind(this);
    this.handleCouponSubmit = this.handleCouponSubmit.bind(this);
  }

  componentDidMount() {
    this.queryCart();
  }

  handleCartItemRemoval(cartItemId) {
    $.ajax({
        type: 'POST',
        url: 'api/cart/removeCartItem',
        data: { cartItemId : cartItemId },
        success: (cart) => {
           this.queryCart();
           refreshProductCounter();
        },
        error: function(data) {
            console.log(data);
        }
    });
  }

  handleCartItemQuantityChange(event, cartItemId) {
    $.ajax({
        type: 'PUT',
        url: 'api/cart/updateCartItem',
        data: {
                cartItemId : cartItemId,
                quantity : event.target.value
              },
        success: (data) => {
            this.queryCart();
            refreshProductCounter();
        },
        error: function(data) {
            console.log(data);
        }
    });
  }

  handleCouponChange(event) {

    if (event.target.value < 7)
      this.setState({ coupon: event.target.value.toUpperCase() });
  }

  handleCouponSubmit() {


  }

  queryCart() {
    $.ajax({
      type: 'GET',
      url: 'api/cart/getCart',
      success: (cart) => {
        this.setState({
            cartItems: cart.cartItems,
            grandTotal: cart.grandTotal
        });
      },
      error: function (error) {
        console.log(error);
      }
    });
  }

  render() {
    if (this.state.cartItems.length < 1) return /*#__PURE__*/React.createElement("div", {
      className: "emptyCartLabel",

    }, "Cart is empty");

    return /*#__PURE__*/React.createElement("div", { id: 'cart' }, /*#__PURE__*/React.createElement("table", null, /*#__PURE__*/React.createElement("thead", null, /*#__PURE__*/React.createElement("tr", null, /*#__PURE__*/React.createElement("th", null), /*#__PURE__*/React.createElement("th", null, "Price"), /*#__PURE__*/React.createElement("th", null, "Quantity"), /*#__PURE__*/React.createElement("th", null, "Total"))), /*#__PURE__*/React.createElement("tbody", {
      id: "cartTableBody"
    }, this.state.cartItems.map((cartItem, i) => /*#__PURE__*/React.createElement("tr", {
      key: i,
    }, /*#__PURE__*/React.createElement("td", {
      className: "productName"
    }, /*#__PURE__*/React.createElement("img", {
      src: ("resources/images/products/" + cartItem.product.mainPicture.fullName)
    }), /*#__PURE__*/React.createElement("span", null, cartItem.product.name)), /*#__PURE__*/React.createElement("td", null, cartItem.product.unitPrice, "$"), /*#__PURE__*/React.createElement("td", null, /*#__PURE__*/React.createElement("div", { className: 'itemQuantity' }, /*#__PURE__*/React.createElement("input", {
      class: "quantityCounter",
      type: "number",
      min: "1",
      max: "9999",
      name: "quantity",
      value: cartItem.quantity,
      onChange: (event) => { this.handleCartItemQuantityChange(event, cartItem.cartItemId) }
    }), /*#__PURE__*/React.createElement("span", {
      className: "discard",
      onClick: () => { this.handleCartItemRemoval(cartItem.cartItemId) },
    }, "\xD7"))), /*#__PURE__*/React.createElement("td", { className: 'total' }, cartItem.total, "$"))))), /*#__PURE__*/React.createElement("div", {
      id: "coupon"
    }, /*#__PURE__*/React.createElement("input", {
      type: "text",
      placeholder: "Have a coupon?",
      value: this.state.coupon,
      onChange: this.handleCouponChange
    }), /*#__PURE__*/React.createElement("button", {
        onClick: this.handleCouponSubmit,
    }, "Apply")), /*#__PURE__*/React.createElement("div", {
      id: "grandTotal",
      className: "clause"
    }, /*#__PURE__*/React.createElement("p", null, "Grand Total"), /*#__PURE__*/React.createElement("span", {
      id: "grandTotalValue"
    }, this.state.grandTotal
    ), /*#__PURE__*/React.createElement("span", {
      id: "grantTotalCurrency"
    }, "$")),
        React.createElement("div", {
          id: "checkout-clause",
          class: "clause"
        }, /*#__PURE__*/React.createElement("button", null, "Checkout"))
    )
  }

}

export default Cart;