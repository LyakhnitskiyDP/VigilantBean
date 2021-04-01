
class CommentSection extends React.Component {

    render() {

        return React.createElement(
            'div',
            null,
            React.createElement(
                AddComment
            ),
            React.createElement(
                ExistingComments
            )
        );

    }

}

class AddComment extends React.Component {

    render() {
        return React.createElement(
            'div',
            null,
            React.createElement(
                'textarea',
                {placeholder: 'Your comment here...'}
            ),
            React.createElement(
                'button',
                {value: 'Add'}
            )
        )
    }
}

class ExistingComments extends React.Component {

    render() {
        return React.createElement('div', null);
    }
}

export default CommentSection