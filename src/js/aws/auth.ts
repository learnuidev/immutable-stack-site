import { Auth } from 'aws-amplify';

export async function signUp ({ username, password, email, phoneNumber }) {
    try {
        const { user } = await Auth.signUp({
            username,
            password,
            attributes: {
                email, // optional
                phone_number: phoneNumber, // optional - E.164 number convention
                // other custom attributes
            },
        });
        console.log(`User signed up successfully`, user);
        return user;
    } catch (error) {
        console.log('error signing up:', error);
    }
}

export async function confirmSignUp ({ username, code }) {
    try {
        const resp = await Auth.confirmSignUp(username, code);
        return resp;
    } catch (error) {
        console.log('error confirming sign up', error);
    }
}

export async function signIn ({ username, password }) {
    try {
        const user = await Auth.signIn(username, password);
        const userAttributes = user.attributes;
        const { refreshToken, accessToken } = user.getSignInUserSession();

        const formattedUser = {
            refreshToken: refreshToken.getToken(),
            jwtToken: accessToken.jwtToken,
            ...userAttributes,
        };

        console.log('user', formattedUser);

        return formattedUser;
    } catch (error) {
        console.log('error signing in', error);
    }
}

export async function resendCode (username) {
    try {
        const resp = await Auth.resendSignUp(username);
        console.log('code resent successfully');
        return resp;
    } catch (err) {
        console.log('error resending code: ', err);
    }
}

async function signOut () {
    try {
        const resp = await Auth.signOut({ global: true });
        return resp;
    } catch (error) {
        console.log('error signing out: ', error);
    }
}

const auth = {
    Auth,
};

export { auth };
