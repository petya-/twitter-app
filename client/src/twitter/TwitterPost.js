import React, { Component } from 'react'
import './TwitterPost.css';
class TwitterPost extends Component {

  constructor () {
    super()
    this.submitPost = this.submitPost.bind(this)
  }

  submitPost () {
    console.log('Success!')
  }
  render() {
    return (
      <div className="content __wait">
        <h1>Twitter Post</h1>
        <h3>filter your thoughts</h3>
        <div className="_secondary">
            <form className="form">
              <select className="select">
                  <option>Vegetable</option>
                  <option>Mean words</option>
                  <option>Me</option>
              </select>
              <button onClick={this.submitPost}>Send</button>
              <input id="name" placeholder="Your Name Goes Here..."/>
              <textarea className="_twemoji_input" rows="7"></textarea>
            </form>
            <h3 id="message">
              <img className="emoji" alt="emojiImg" src="https://twemoji.maxcdn.com/36x36/1f64c.png"/>
              Show your message to the world
              <img className="emoji" alt="emojiImg" src="https://twemoji.maxcdn.com/36x36/1f30d.png"/>
            </h3>
            <ul className="messages"></ul>
        </div>
      </div>
    )
  }

}
export default TwitterPost
