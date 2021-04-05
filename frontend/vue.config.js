module.exports = {
  outputDir: 'build/dist/public',
  chainWebpack(config) {
    config.module
      .rule('pug')
      .test(/\.pug$/)
      .use('pug-plain-loader')
      .loader('pug-plain-loader')
      .end()
  }
}
