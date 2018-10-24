"use strict";

export class TreeNode {
  /* #region Instance variables */
  private _parentNode: TreeNode | null;
  private _nodeData:any = {
  };
  private _childNodes: TreeNode[] | null = new Array<TreeNode>();
  /* #endregion */

  constructor() {}

  /* #region Getters and Setters */
  set parentNode(parentNode: TreeNode | null) {
    this._parentNode = parentNode;
  }
  get parentNode(): TreeNode | null {
    return this!._parentNode;
  }

  set nodeData(nodeData) {
    this._nodeData = nodeData;
  }
  get nodeData() {
    return this._nodeData;
  }

  set childNodes(childNodes: TreeNode[] | null) {
    this._childNodes = childNodes;
  }
  get childNodes(): TreeNode[] | null {
    return this._childNodes;
  }
  /* #endregion */

  /* #region Methods */
  isRootNode(): boolean {
    return this.parentNode == null;
  }
  isLeafNode(): boolean {
    return this._childNodes == null || this._childNodes.length<1;
  }
  /* #endregion */
  
}
