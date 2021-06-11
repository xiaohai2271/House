const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");
const Config = require("webpack-chain");

const config = new Config();
const distPath = "../../../src/main/resources/templates";

config.entry("index").add(path.resolve(__dirname, "../src/index.tsx"));

config.module
  .rule("jsts")
  .test(/\.[jt]sx?$/)
  .exclude.add(/node_modules/)
  .end()
  .use("ts")
  .loader("ts-loader")
  .end();

config.module
  .rule("css")
  .test(/\.css$/)
  .use("css")
  .loader("css-loader")
  .end()
  .use("postcss")
  .loader("postcss-loader")
  .end();

config.module
  .rule("less")
  .test(/\.less$/)
  .use("css")
  .loader("css-loader")
  .end()
  .use("postcss")
  .loader("postcss-loader")
  .end()
  .use("less")
  .loader("less-loader")
  .end();

config.module
  .rule("file")
  .test(/\.(png|jpe?g|gif|svg|woff|woff2|ttf|eot)$/i)
  .use("url")
  .loader("url-loader")
  .options({
    limit: 8192,
  });

config.resolve.extensions
  .add("*")
  .add(".js")
  .add(".jsx")
  .add(".ts")
  .add(".tsx")
  .end();

config.resolve.alias.set("@", path.resolve(__dirname, "../src/"));

config.output
  .path(path.resolve(__dirname, distPath))
  .filename("[name].[contenthash].js")
  .chunkFilename("[name].[contenthash].js")
  .publicPath("/");

config.plugin("html").use(HtmlWebpackPlugin, [
  {
    template: "src/index.ejs",
    title: "House",
  },
]);

module.exports = { config, distPath };
