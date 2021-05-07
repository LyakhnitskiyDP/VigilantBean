import Cart from './components/Cart_Component.js';

$(document).ready(function() {

    ReactDOM.render(
        React.createElement(Cart, null),
        document.getElementById('cart')
    )

})