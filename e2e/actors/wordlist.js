
module.exports = function() {
    return actor({
        formField: {
            password : 'password',
            username: 'username',
            passwordConfirmation: 'Confirm Password',
            SubmitButton: 'Submit'
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
            password: 'pass1234'
        }

    });
}
