import CommentSection from './components/CommentSectionComponent.js';

$(document).ready(function() {

    ReactDOM.render(
        React.createElement(CommentSection, null),
        document.getElementById('comment-clause')
    )
})
