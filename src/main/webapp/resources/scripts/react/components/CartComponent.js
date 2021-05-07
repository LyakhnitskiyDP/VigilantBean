
class Cart extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            cartItems: []
        };

        this.queryCart = this.queryCart.bind(this);
    }

    componentDidMount() {

    }

    queryCart() {

        $.ajax({
           type: 'GET',
           url: 'api/cart/getCart',
           success: function(cart) {
            console.log(cart);
           },
           error: function(error) {
            console.log(error);
           }
       });

    }

    render() {

        if (this.state.cartItems.length < 1)
            return (<div className="emptyCartLabel">Cart is empty</div>);

        return (
        <div>
            <table>
                <thead>
                    <tr>
                        <th></th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Total</th>
                    </tr>
                </thead>

                <tbody id="cartTableBody">

                    {
                     this.state.cartItems.map(
                        (cartItem, i) => (
                            <tr key="{i}" id="cartItem_{cartItem.cartItemId}">
                              <td class="productName">
                                <img src="resources/images/products/{cartItem.product.mainPicture.fullName}"/>
                                <span>{cartItem.product.name}</span>
                              </td>
                              <td>{cartItem.product.unitPrice}$</td>
                              <td>
                                <div>
                                  <input class="quantityCounter" type="number" min="1" max="9999"
                                   name="quantity" value="{cartItem.quantity}" />
                                  <span class="discard">&times;</span>
                                </div>
                              </td>
                              <td>${cartItem.total}$</td>
                            </tr>
                        )
                     )
                    }

                </tbody>
            </table>

            <div id="coupon">
                <input type="text" placeholder="Have a coupon?"/>
                <button>Apply</button>
            </div>

            <div id="grandTotal" className="clause">
                <p>Grand Total</p>
                <span id="grandTotalValue"></span>
                <span id="grantTotalCurrency">$</span>
            </div>
        </div>
        );

    }

}