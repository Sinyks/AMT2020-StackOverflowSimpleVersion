
module.exports = function() {
    return actor({
        formField: {
            password : 'Password',
            username: 'Username',
            email: 'Email',
            passwordConfirmation: 'Confirm Password',
            SubmitButton: 'Submit',
            Qlabel: 'title',
            Qcontent: 'body'
        },
        pageUrl: {
            login: '/login',
            root: '/',
            register: '/register',
            private: '/private',
            askQuestion: '/questions'
        },
        Data: {
            usernameUnique: `User-${Date.now()}`,
            emailUnique: `${Date.now()}@mail.com`,
            password: 'pass1234',
            questionTitle: 'the question',
            questionContent: 'to be or not to be?',
            answerContent : `... not to be ${Date.now()}`,
            commentContent : `I see you are a man of culture ${Date.now()}`
        }

    });
}
