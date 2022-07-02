import { useQuery, useMutation, useQueryClient, QueryClient, QueryClientProvider as QueryProvider } from 'react-query';
import { ReactQueryDevtools } from 'react-query/devtools';

export const queryClient = new QueryClient();

export const QueryClientProvider = props => {
    return (
        <QueryProvider client={queryClient}>
            {props.children}

            <ReactQueryDevtools initialIsOpen={false} />
        </QueryProvider>
    );
};
