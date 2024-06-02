import { PackageJsons } from './project-package-json';
declare const PackageJsonsByBaseDir: PackageJsons;
declare function searchPackageJsonFiles(baseDir: string, exclusions: string[]): void;
declare function getNearestPackageJsons(file: string): import("./project-package-json").PackageJson[];
declare function getAllPackageJsons(): Map<string, import("./project-package-json").PackageJson>;
/**
 * Retrieve the dependencies of all the package.json files available for the given file.
 *
 * @param fileName context.filename
 * @param cache Cache for the available dependencies by dirname.
 * @param dirCache Cache for each dirname the dependencies of the package.json in this directory, empty set when no package.json.
 * @returns
 */
declare function getDependencies(fileName: string): Set<string>;
export { searchPackageJsonFiles, getNearestPackageJsons, getAllPackageJsons, getDependencies, PackageJsonsByBaseDir, };
export { PackageJson } from './project-package-json';
