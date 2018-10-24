'use strict'

import { Fujiko } from "./interface.ts"

declare const Buffer

export class Token {
	public static decode(token: String): Fujiko {
		let req: string[] = token.split(".")
		let cred = JSON.parse(Buffer.from(req[1], 'base64').toString('ascii'))
		let user: string[] = cred.sub.split("_")
		return <Fujiko> {
			id: parseInt(user[0]),
			name: user[1],
			iss: cred.iss,
			date: cred.date,
			role: cred.auth
		}
	}
}