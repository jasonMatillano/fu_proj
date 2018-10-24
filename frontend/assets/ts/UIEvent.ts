"use strict";

export class UIEvent {

  constructor(private _key:number,private _eventName: string, private _isActive: boolean) {}

  /* #region Getters and Setters */
  set key(key: number) {
    this._key = key;
  }
  get key(): number {
    return this._key;
  }
  set eventName(eventName: string) {
    this._eventName = eventName;
  }
  get eventName(): string {
    return this._eventName;
  }
  set isActive(isActive: boolean) {
    this._isActive = isActive;
  }
  get isActive(): boolean {
    return this._isActive;
  }
  /* #endregion */
}
