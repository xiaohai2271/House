import * as React from "react";
import {hot} from "react-hot-loader";

import "../assets/less/App.less";

class App extends React.Component<Record<string, unknown>, undefined> {
    public render() {
        return (
            <div className="app">
                <h1>Hello World!</h1>
                <p>Foo to the barz</p>
            </div>
        );
    }
}

declare let module: Record<string, unknown>;

export default hot(module)(App);
