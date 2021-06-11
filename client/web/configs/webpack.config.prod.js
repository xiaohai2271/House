const path = require("path");
const { CleanWebpackPlugin } = require("clean-webpack-plugin");
const CopyPlugin = require("copy-webpack-plugin");
const MiniCssExtractPlugin = require("mini-css-extract-plugin");

const { config, distPath } = require("./webpack.common");

config.mode("production");

config.module
  .rule("css")
  .use("mini-css-extract")
  .before("css")
  .loader(MiniCssExtractPlugin.loader)
  .end();

config.module
  .rule("less")
  .use("mini-css-extract")
  .before("css")
  .loader(MiniCssExtractPlugin.loader)
  .end();

config.devtool("source-map");

config.plugin("mini-css-extract").use(MiniCssExtractPlugin);

config.plugin("clean").use(CleanWebpackPlugin);

config.plugin("copy").use(CopyPlugin, [
  {
    patterns: [
      {
        from: path.resolve(__dirname, "../public/"),
        to: path.resolve(__dirname, distPath),
      },
    ],
  },
]);

module.exports = config.toConfig();
