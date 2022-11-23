export class ConnReq {
    id!: number;
	name!: string;
	gender!: string;
	district!: string;
	state!: string;
	pinCode!: string;
	ownership!: string;
	govtId!: string;
	idNumber!: string;
	category!: string;
	loadAppl!: Number;
	dateOfApplication!: Date;
	dateOfApproval!: Date | null;
	modifiedDate!: Date;
	status!: string;
	reviewerId!: Number;
	reviewerName!: string;
	comments!: string;
}