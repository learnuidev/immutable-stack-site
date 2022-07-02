const ROOT_URL = `http://localhost:8080/api/v1`;

export const StudentService = {
    browse: async () => {
        const students = await fetch(`${ROOT_URL}/authed/students`);
        return students.json();
    },
};
