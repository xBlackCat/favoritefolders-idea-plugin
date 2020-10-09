package org.xblackcat.idea.favorites;

import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.ui.ImageUtil;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author xBlackCat
 */

@SuppressWarnings("UnusedDeclaration")
enum FolderIcon implements IIconGetter {
    Default(load("/nodes/folder.svg")),
    Custom(load("/nodes/newFolder.svg")),
    AbstractClass(load("/nodes/abstractClass.svg")),
    AbstractException(load("/nodes/abstractException.svg")),
    AbstractMethod(load("/nodes/abstractMethod.svg")),
    AnnotationType(load("/nodes/annotationtype.svg")),
    AnonymousClass(load("/nodes/anonymousClass.svg")),
    Artifact(load("/nodes/artifact.svg")),
    C_plocal(load("/nodes/c_plocal.svg")),
    C_private(load("/nodes/c_private.svg")),
    C_protected(load("/nodes/c_protected.svg")),
    C_public(load("/nodes/c_public.svg")),
    Class(load("/nodes/class.svg")),
    ClassInitializer(load("/nodes/classInitializer.svg")),
    CompiledClassesFolder(load("/nodes/compiledClassesFolder.svg")),
    CopyOfFolder(load("/nodes/copyOfFolder.svg")),
    Cvs_global(load("/nodes/cvs_global.svg")),
    Cvs_roots(load("/nodes/cvs_roots.svg")),
    DataColumn(load("/nodes/dataColumn.svg")),
    DataSchema(load("/nodes/dataSchema.svg")),
    dataTables(load("/nodes/DataTables.svg")),
    Deploy(load("/nodes/deploy.svg")),
    Ejb(load("/nodes/ejb.svg")),
    EjbBusinessMethod(load("/nodes/ejbBusinessMethod.svg")),
    EjbCmpField(load("/nodes/ejbCmpField.svg")),
    EjbCmrField(load("/nodes/ejbCmrField.svg")),
    EjbCreateMethod(load("/nodes/ejbCreateMethod.svg")),
    EjbFinderMethod(load("/nodes/ejbFinderMethod.svg")),
    EjbPrimaryKeyClass(load("/nodes/ejbPrimaryKeyClass.svg")),
    EjbReference(load("/nodes/ejbReference.svg")),
    EnterpriseProject(load("/nodes/enterpriseProject.svg")),
    Enum(load("/nodes/enum.svg")),
    ErrorIntroduction(load("/nodes/errorIntroduction.svg")),
    ExceptionClass(load("/nodes/exceptionClass.svg")),
    ExtractedFolder(load("/nodes/extractedFolder.svg")),
    Field(load("/nodes/field.svg")),
    FieldPK(load("/nodes/fieldPK.svg")),
    Folder(load("/nodes/folder.svg")),
    Function(load("/nodes/function.svg")),
    HomeFolder(load("/nodes/homeFolder.svg")),
    IdeaModule(load("/nodes/ideaModule.svg")),
    IdeaProject(load("/nodes/ideaProject.svg")),
    InspectionResults(load("/nodes/inspectionResults.svg")),
    Interface(load("/nodes/interface.svg")),
    JarDirectory(load("/nodes/jarDirectory.svg")),
    JavaDocFolder(load("/nodes/javaDocFolder.svg")),
    Jsr45(load("/nodes/jsr45.svg")),
    KeymapAnt(load("/nodes/keymapAnt.svg")),
    KeymapEditor(load("/nodes/keymapEditor.svg")),
    KeymapMainMenu(load("/nodes/keymapMainMenu.svg")),
    KeymapOther(load("/nodes/keymapOther.svg")),
    KeymapTools(load("/nodes/keymapTools.svg")),
    Method(load("/nodes/method.svg")),
    Module(load("/nodes/Module.svg")),
    ModuleGroup(load("/nodes/moduleGroup.svg")),
    NewException(load("/nodes/newException.svg")),
    NewFolder(load("/nodes/newFolder.png")),
    NewParameter(load("/nodes/newParameter.svg")),
    NodePlaceholder(load("/nodes/nodePlaceholder.svg")),
    Package(load("/nodes/package.svg")),
    Padlock(load("/nodes/padlock.svg")),
    Parameter(load("/nodes/parameter.svg")),
    Plugin(load("/nodes/plugin.svg")),
    PluginNotInstalled(load("/nodes/pluginnotinstalled.svg")),
    PluginObsolete(load("/nodes/pluginobsolete.svg")),
    Pointcut(load("/nodes/pointcut.svg")),
    PpFile(load("/nodes/ppFile.png")),
    PpInvalid(load("/nodes/ppInvalid.svg")),
    PpJar(load("/nodes/ppJar.svg")),
    PpJdk(load("/nodes/ppJdk.svg")),
    PpLib(load("/nodes/ppLib.svg")),
    PpLibFolder(load("/nodes/ppLibFolder.svg")),
    PpWeb(load("/nodes/ppWeb.svg")),
    Project(load("/nodes/project.svg")),
    Property(load("/nodes/property.svg")),
    PropertyRead(load("/nodes/propertyRead.svg")),
    PropertyReadStatic(load("/nodes/propertyReadStatic.svg")),
    PropertyReadWrite(load("/nodes/propertyReadWrite.svg")),
    PropertyReadWriteStatic(load("/nodes/propertyReadWriteStatic.svg")),
    PropertyWrite(load("/nodes/propertyWrite.svg")),
    PropertyWriteStatic(load("/nodes/propertyWriteStatic.svg")),
    ReadAccess(load("/nodes/read-access.svg")),
    ResourceBundle(load("/nodes/resourceBundle.svg")),
    RwAccess(load("/nodes/rw-access.svg")),
    SecurityRole(load("/nodes/SecurityRole.png")),
    Servlet(load("/nodes/servlet.svg")),
    SortBySeverity(load("/nodes/sortBySeverity.svg")),
    Static(load("/nodes/static.svg")),
    TestSourceFolder(load("/nodes/testSourceFolder.svg")),
    Undeploy(load("/nodes/undeploy.svg")),
    UnknownJdk(load("/nodes/unknownJdk.svg")),
    UpFolder(load("/nodes/upFolder.svg")),
    UpLevel(load("/nodes/upLevel.svg")),
    Variable(load("/nodes/variable.svg")),
    WarningIntroduction(load("/nodes/warningIntroduction.svg")),
    WebFolder(load("/nodes/webFolder.svg")),
    WebListener(load("/nodes/weblistener.svg")),
    WriteAccess(load("/nodes/write-access.svg"));

    private final Icon icon;

    FolderIcon(Icon icon) {
        this.icon = icon;
    }

    @Override
    public Icon getIcon() {
        return icon;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public boolean isCustom() {
        return false;
    }

    public static IIconGetter loadIcon(String url) throws IOException {
        VirtualFile file = VirtualFileManager.getInstance().findFileByUrl(url);
        if (file == null) {
            // File no longer exists - return common custom folder icon.
            return Custom;
        }

        ImageIcon icon = new ImageIcon(file.contentsToByteArray());

        int height = icon.getIconHeight();
        int width = icon.getIconWidth();
        if (height > 16 || width > 16) {
            BufferedImage im = ImageUtil.createImage(16, 16, BufferedImage.TYPE_4BYTE_ABGR);

            if (height == width) {
                im.getGraphics().drawImage(icon.getImage(), 0, 0, 16, 16, null);
            } else if (height > width) {
                int newHeight = (int) ((height * 16.) / width);
                im.getGraphics().drawImage(icon.getImage(), 0, 0, 16, newHeight, null);
            } else {
                int newWidth = (int) ((width * 16.) / height);
                im.getGraphics().drawImage(icon.getImage(), 0, 0, newWidth, 16, null);
            }


            icon = new ImageIcon(im);
        }

        return new CustomIcon(url, icon);
    }

    public static IIconGetter getIcon(String iconName) {
        try {
            return FolderIcon.valueOf(iconName);
        } catch (IllegalArgumentException e) {
            // Fall through - check external icon
        }

        // Check if the name is uri
        try {
            return loadIcon(iconName);
        } catch (IOException e) {
            // Fall through
        }

        return Default;
    }

    private static final class CustomIcon implements IIconGetter {
        private final String url;
        private final Icon icon;

        private CustomIcon(String url, Icon icon) {
            this.url = url;
            this.icon = icon;
        }

        @Override
        public Icon getIcon() {
            return icon;
        }

        @Override
        public String getName() {
            return url;
        }

        @Override
        public boolean isCustom() {
            return true;
        }
    }

    private static Icon load(String path) {
        return IconLoader.getIcon(path, FolderIcon.class);
    }
}
