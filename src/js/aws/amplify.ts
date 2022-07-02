import { Amplify } from 'aws-amplify';

export function configure (config) {
    return Amplify.configure(config);
}
