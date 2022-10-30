export enum SubmissionStatus {
	Undetermined,
	Incorrect,
	Correct
}

export type AuthenticatedUser = {
	email: string;
	name: string;
	given_name: string;
	picture: string;
};
