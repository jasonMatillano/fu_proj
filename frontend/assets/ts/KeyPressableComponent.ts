import { Component, Prop, Vue } from "nuxt-property-decorator";
import { KeyPressable } from "./interface/KeyPressable.ts";
import { Keyboard } from "./keyboard.ts";
import { UIEvent } from "@/assets/ts/UIEvent.ts";

@Component({})
export default class KeyPressableComponent extends Vue implements KeyPressable {
  /* #region Props */
  @Prop({ default: null })
  events: Array<UIEvent> | null;
  /* #endregion */

  /* #region Instance variables */
  UIEvents: Array<UIEvent> | null;
  keyboard: Keyboard;
  keys: Array<number> = new Array();
  /* #endregion */

  /* #region Instance Methods */
  mounted() {
    this.keyboard = Keyboard.getInstance;
    this.mapEvents();
  }

  /**
   * Add keypress listener to each events.
   */
  mapEvents() {
    this.UIEvents = this.events;
    if (this.UIEvents) {
      this.UIEvents!.forEach(e => {
        this.addKeyPressListener(e.key);
      });
    }
  }

  /**
   * Adds keypress listener and its event handler to a keycode.
   * Calls keyPressEventHandler method by default.
   * @param keyCode Keyboard code
   * @param onKeyPress Event handler
   */
  addKeyPressListener(
    keyCode: number,
    onKeyPress: ((this: void, keyCode: any) => void) = this.keyPressEventHandler
  ) {
    this.keys.push(keyCode);
    this.keyboard.addPress(keyCode, onKeyPress);
  }
  /**
   * Default Keypress event handler
   * @param keyPressEvent
   */
  keyPressEventHandler(keyPressEvent): void {
    let event = this.getEventByKeyCode(keyPressEvent.keyCode);
    if (event && event.isActive) this.$emit("eventTriggered", event);
  }
  /**
   * Gets the corresponding UIEvent object of a supplied keyCode 
   * @param keyCode
   * @returns UIEvent
   */
  private getEventByKeyCode(keyCode: number): UIEvent {
    for (let event of this.events) {
      if (event.key == keyCode) return event;
    }
    return null;
  }
  /* #endregion */
}
