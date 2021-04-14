
class CommentSection extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            userIsAuthenticated: false,
            currentPage: 1,
            comments: [],
            commentsPerPage: 3,
            pages: []
        }

        this.productId = $('#comment-clause').attr('productId');
        this.handlePageChange = this.handlePageChange.bind(this);
        this.queryComments = this.queryComments.bind(this);

        this.onCommentSubmit = this.onCommentSubmit.bind(this);
    }

    componentDidMount() {
        this.queryComments();
    }

    handlePageChange(event) {

        this.setState({
            currentPage: event.target.innerHTML
        }, () => { this.queryComments(); });
    }

    onCommentSubmit() {
        this.queryComments();
    }

    queryComments() {
       $.ajax({
           type: 'GET',
           contentType: 'application/json',
           url: 'http://localhost:8080/vigilantBean/api/comment/getComments',
           data: {
               productId: this.productId,
               currentPage: this.state.currentPage,
               commentsPerPage: this.state.commentsPerPage
           },
           timeout: 600000,
           success: (data) => {
               this.setState({
                   pages: data.pages,
                   comments: data.entities
               });
           },
           error: function(jqXHR, exception) {
               console.log(exception);
           }
       });
    }

    render() {

        return React.createElement(
            'div',
            null,
            React.createElement(
                AddComment,
                {
                    productId: this.productId,
                    onCommentSubmit: this.onCommentSubmit
                }
            ),
            React.createElement(
                ExistingComments,
                {
                    comments: this.state.comments,
                    pages: this.state.pages,
                    commentsPerPage: this.state.commentsPerPage,
                    handlePageChange: this.handlePageChange
                }
            )
        );

    }

}

class AddComment extends React.Component {

    constructor(props) {
        super(props);

        this.state = {
            commentContent: '',
            stars: 8,
            userIsAuthenticated: true
        }

        this.handleCommentContentChange = this.handleCommentContentChange.bind(this);
        this.handleCommentStarsChange = this.handleCommentStarsChange.bind(this);
        this.handleCommentSubmit = this.handleCommentSubmit.bind(this);

        this.handleStarsDecrease = this.handleStarsDecrease.bind(this);
        this.handleStarsIncrease = this.handleStarsIncrease.bind(this);

    }

    componentDidMount() {

        $.ajax({
                type: 'GET',
                url: 'http://localhost:8080/vigilantBean/api/user/isAuthenticated',
                success: (data) => {
                    this.setState({
                        userIsAuthenticated: data
                    });
                },
                error: function(error) {
                    console.log(error);
                }
               });

    }

    handleCommentContentChange(event) {

        this.setState({
            commentContent: event.target.value,
        });
    }

    handleCommentStarsChange(event) {

        let starsToSet = event.target.value;

        if (starsToSet > 10) starsToSet = 10;
        if (starsToSet < 0) starsToSet = 0;

        this.setState({
            stars: starsToSet,
        });
    }

    handleStarsIncrease() {

        this.setState((prevState) => {
            const prevStars = prevState.stars;

            if (prevStars >= 10) return { stars: 10 };
            else return { stars: (prevStars + 1) };
        });
    }

    handleStarsDecrease() {

        this.setState((prevState) => {
            const prevStars = prevState.stars;

            if (prevStars <= 0) return { stars: 0 }
            else return { stars: (prevStars - 1) }
        });
    }

    handleCommentSubmit() {

        $('#submitComment').prop('disabled', true);
        $('#newCommentContent').prop('disabled', true);

        const commentToAdd = {};
        commentToAdd['productId'] = this.props.productId;
        commentToAdd['content'] = this.state.commentContent;
        commentToAdd['stars'] = this.state.stars;

        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: 'http://localhost:8080/vigilantBean/api/comment/addComment',
            data: JSON.stringify(commentToAdd),
            timeout: 600000,
            success: (data) => {
                 $('#submitComment').prop('disabled', false);
                 $('#newCommentContent').prop('disabled', false);
                 this.setState({ commentContent: '', stars: 8})
                 this.props.onCommentSubmit();
            },
            error: function(jqXHR, exception) {
                 console.log('Failed to add a new comment');
                 console.log(exception);
                 $('#submitComment').prop('disabled', false);
                 $('#newCommentContent').prop('disabled', false);
            }
        });

    }

    render() {
        const addCommentElement =
            React.createElement(
                    'div',
                    {
                        id: 'addNewComment'
                    },
                    React.createElement(
                        'textarea',
                        {
                         placeholder: 'Your comment here...',
                         id: 'newCommentContent',
                         onChange: this.handleCommentContentChange,
                         value: this.state.commentContent
                        }
                    ),
                    React.createElement(
                        'div',
                        {
                         id: 'newCommentStarsClause'
                        },
                        React.createElement(
                            'div',
                            {
                             id: 'starsInput',
                            },
                            React.createElement(
                                'div',
                                {
                                 style: {
                                     height: '2em',
                                     minWidth: '2em',
                                     minHeight: '2em',
                                     display: 'flex',
                                 }
                                },
                                React.createElement(
                                    'img',
                                    {
                                     src: 'http://localhost:8080/vigilantBean/resources/images/icons/left-pointing-triangle.svg',
                                     onClick: this.handleStarsDecrease
                                    }
                                ),
                            ),
                            React.createElement(
                                'input',
                                {
                                    type: 'number',
                                    min: '1',
                                    value: this.state.stars,
                                    max: '10',
                                    onChange: this.handleCommentStarsChange,
                                    value: this.state.stars,
                                    id: 'newCommentStars',
                                    style: { color: getColor(this.state.stars) }
                                }
                            ),
                            React.createElement(
                                'img',
                                {
                                 src: 'http://localhost:8080/vigilantBean/resources/images/icons/left-pointing-triangle.svg',
                                 className: 'starsButton',
                                 onClick: this.handleStarsIncrease,
                                 id: 'stars-down',
                                }
                            ),
                        )
                    ),
                    React.createElement(
                        'button',
                        {
                            id: 'submitComment',
                            onClick: this.handleCommentSubmit,
                        },
                        'Add'
                    )
            );

        const unauthenticatedMessage =
            React.createElement(
                'p',
                {
                    className: 'errorMessage'
                },
                'Please, sign in in order to leave a comment.'
                )

        return this.state.userIsAuthenticated ? addCommentElement : unauthenticatedMessage;
    }
}

function ExistingComments(props) {

    return React.createElement(
        'div',
        {
         id: 'existingCommentsSection',
        },
        React.createElement(
            'div',
            {
             id: 'existingComments',
            },
            props.comments.map(
                (comment) => (
                    React.createElement(
                        'div',
                        {
                         className: 'comment'
                        },
                        React.createElement(
                            'div',
                            {
                             className: 'commentHeader'
                            },
                            React.createElement(
                                'span',
                                {
                                 className: 'commentUserName',
                                },
                                comment.appUser.username
                            ),
                            React.createElement(
                                'span',
                                {
                                 className: 'commentUserStars',
                                 style: { color: getColor(comment.stars) }
                                },
                                comment.stars + '/10'
                            )
                        ),
                        React.createElement(
                            'div',
                            {
                             className: 'existingCommentContent',
                            },
                            comment.content
                        ),
                        React.createElement(
                            'div',
                            {
                             className: 'commentFooter',
                            },
                            React.createElement(
                                'span',
                                {
                                 className: 'commentCreationDate',
                                },
                                parseNanos(comment.creationDate)
                            )
                        )
                    )
                )
            )
        ),
        React.createElement(
            'div',
            {
             id: 'existingCommentsPageList',
            },
            props.pages.map(
                (page) => (React.createElement(
                    'div',
                    {
                     className: 'page',
                     id: (page == props.currentPage ? 'currentPage' : undefined),
                     onClick: props.handlePageChange,
                    },
                    page)
                )
            )
        )
    );
}

/* Helper Functions */
function parseNanos(nanos) {
    const date = new Date(nanos);

    const fullYear = date.getFullYear();
    const month = appendIfNeeded(date.getMonth() + 1);
    const day = appendIfNeeded(date.getUTCDate());
    const hours = appendIfNeeded(date.getHours());
    const minutes = appendIfNeeded(date.getMinutes());

    return `${fullYear}/${month}/${day} ${hours}:${minutes}`;

    function appendIfNeeded(timeUnit) {

        return timeUnit < 10 ? ('0' + timeUnit) : timeUnit;
    }
}

function getColor(stars) {

    if (stars > 6)
        return 'gold';

    if (stars > 3)
        return 'bisque';

    return 'brown';

}

export default CommentSection;