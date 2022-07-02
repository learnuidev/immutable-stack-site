# Getting started with ClojureScript, TypeScript and TailwindCSS

In this video you will learn how to build SPA using TypeScript, ClojureScript, tailwindcss and headless


Learning Objects

- Create basic shadow-cljs react project
- Add typescript into our project.
- Learn how to configure a typescript project + understand all the required dependencies
- Learn what is babel and how to configure + use to compile typescript into es2015 code
- Use babel to compile typescript to javascript
- Learn how to use custom TypeScript component in ClojureScript codebase
- Learn how to use custom hooks in Reagent based application


So here is the strategy

1. we will use typescript to write all of our custom javascript interop logic such as react components that uses headless ui + tailwind ui
2. we will use clojurescript to write only the core business logic

This strategy has many benefits
- JavaScript interop code can evolve independently from core business logic
- Clear separation from interop code and business logic code
- ClojureScript code becomes smaller since it is only using interfaces


Steps:

1. Create a new shadow-cljs project
2. Configure typescript
3. Configure tailwind

_To Run Locally:_
Clone the repository and perform the following command line actions:
```bash
$ git clone https://github.com/learnuidev/tailwind-typescript-clojurescript-example.git
$ cd tailwind-typescript-clojurescript-example
$ yarn
$ yarn start
```

This will do three things

1. Tell babel to watch for changes in typescript files
2. Tell tailwind to watch for tailwind related things
3. Start the development server in port 3000


open http://localhost:3000 in browser.


---
# Fungus - Next Generation Learning Management Platform

## Part A - Frontend Integration

### TypeScript Setup


#### Step 1: Install + Configure Babel

```sh
yarn add @babel/runtime@7.15.4
```

```sh
yarn add @babel/cli@7.15.4 @babel/core@7.15.0 @babel/plugin-proposal-class-properties@7.14.5 @babel/plugin-proposal-object-rest-spread@7.15.6 @babel/plugin-proposal-private-methods@7.14.5 @babel/plugin-proposal-private-property-in-object@7.15.4 @babel/plugin-transform-runtime@7.15.0 @babel/preset-env@7.15.6 @babel/preset-react@7.14.5 @babel/preset-typescript@7.15.0 babel-loader@8.2.2 babel-plugin-module-resolver@4.1.0 babel-plugin-styled-components@1.13.2 babel-plugin-transform-imports@2.0.0 -D

```

Create `babel.config.js`

```js
    const { existsSync, lstatSync } = require("fs");
    const { resolve, dirname } = require("path");

    function isRelativeImport(path){
    return path.startsWith(".");
    }

    function isDirectory(path) {
    return existsSync(path) && lstatSync(path).isDirectory();
    }

    function resolveImport (from, to) {
    return resolve(dirname(from), to);
    }

    function replaceDirectoryImports() {
    return {
        visitor: {
        ImportDeclaration: (path, state) => {
            const importPath = path.node.source.value;
            const fileName = state.file.opts.filename;
            if (isRelativeImport(importPath) && isDirectory(resolveImport(fileName, importPath))) {
            path.node.source.value += "/index";
            }
        }
        }
    }
    }


    // This config will output files to ./src/gen/components via the `yarn components` script
    // See https://shadow-cljs.github.io/docs/UsersGuide.html#_javascript_dialects
    module.exports = {
    presets: [
        "@babel/env",
        // Compile tsx files.
        "@babel/preset-typescript",
        // Use the react runtime import if available.
        ["@babel/preset-react", {"runtime": "automatic"}]
    ],
    plugins: [
        // Add /index to all relative directory imports, because Shadow-CLJS does not support
        // them (https://github.com/thheller/shadow-cljs/issues/841#issuecomment-777323477)
        // NB: Putting these files in node_modules would have fixed the directory imports
        // but broken hot reload (https://github.com/thheller/shadow-cljs/issues/764#issuecomment-663064549)
        replaceDirectoryImports,
        // Allow using @/ for root relative imports in the component library.
        ["module-resolver", {alias: {"@": "./src/js/components"}}],
        // Transform material-ui imports into deep imports for faster reload.
        // material-ui is very big, and importing it all can slow down development rebuilds by a lot.
        // https://material-ui.com/guides/minimizing-bundle-size/#development-environment
        ["transform-imports", {
        "@material-ui/core": {
            transform: "@material-ui/core/esm/${member}",
            preventFullImport: true
        },
        "@material-ui/icons": {
            transform: "@material-ui/icons/esm/${member}",
            preventFullImport: true
        }
        }],
        // Our build doesn't need the {loose: true} option, but if not included it wil
        // show a lot of warnings on the storybook build.
        ["@babel/proposal-class-properties", {loose: true}],
        ["@babel/proposal-object-rest-spread", {loose: true}],
        // Used only by storybook, but must be included to avoid build warnings/errors.
        ["@babel/plugin-proposal-private-methods", {loose: true}],
        ["@babel/plugin-proposal-private-property-in-object", {loose: true}],
        // Import helpers from @babel/runtime instead of duplicating them everywhere.
        "@babel/plugin-transform-runtime",
        // Better debug information for styled components.
        // https://styled-components.com/docs/tooling#babel-plugin
        "babel-plugin-styled-components"
    ],
    // Do not apply this babel config to node_modules.
    // Shadow-CLJS also runs babel over node_modules and we don't want this
    // configuration to apply to it.
    // We still want it to be picked up by storybook though.
    exclude: ["node_modules"]
    }

```


#### Step 2: Install + Configure TypeScript

```sh
yarn add typescript@4.3.5 -D

```

Create `tsconfig.json`
```json
    {
        "compilerOptions": {
            "target": "es2015",
            "module": "commonjs",
            "jsx": "react-jsx",
            "importHelpers": true,
            "strict": false,
            "esModuleInterop": true,
            "skipLibCheck": true,
            "noEmit": true,
            "rootDir": "src/js/components/",
            "baseUrl": "src/js/components",
            "paths": {
                "@/*": [
                    "./*"
                ]
            }
        },
        "include": [
            "src/js/components/**/*.tsx",
            "src/js/components/**/*.ts"
        ]
    }
```

#### Step 3: Convert to deps.edn file


#### Step 4: Add scripts to compile typescript => JS

4.1 Install concurrently
```sh
yarn add concurrently@6.5.1 -D
```

4.2 Update `package.json`
```json
    {
        "scripts": {
            "components": "npx babel ./src/js/components/ --extensions .ts,.tsx --out-dir ./src/gen/components/",
            "components:watch": "yarn components --watch",
            "dev": "yarn components && concurrently \"yarn components:watch\" \"yarn start\"",
        }
    }
```

#### Step 5: Create random component to test


#### Step 6: Start dev server

```sh
yarn dev

```

#### Step 7: Use JS component in ClojureScript App


#### Step 8: Upgrade react to v17
