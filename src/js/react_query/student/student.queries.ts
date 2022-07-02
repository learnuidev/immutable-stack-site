import { useQuery } from 'react-query';
import { QueryId } from '../common.types';
import { StudentService } from '../../services/StudentService';

export const useFetchStudents = () => {
    return useQuery(QueryId.students, StudentService.browse);
};
