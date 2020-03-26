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
    Default(IconLoader.getIcon("/nodes/folder.svg")),
    Custom(IconLoader.getIcon("/nodes/newFolder.svg")),
    AbstractClass(IconLoader.getIcon("/nodes/abstractClass.svg")),
    AbstractException(IconLoader.getIcon("/nodes/abstractException.svg")),
    AbstractMethod(IconLoader.getIcon("/nodes/abstractMethod.svg")),
    AnnotationType(IconLoader.getIcon("/nodes/annotationtype.svg")),
    AnonymousClass(IconLoader.getIcon("/nodes/anonymousClass.svg")),
    Artifact(IconLoader.getIcon("/nodes/artifact.svg")),
    C_plocal(IconLoader.getIcon("/nodes/c_plocal.svg")),
    C_private(IconLoader.getIcon("/nodes/c_private.svg")),
    C_protected(IconLoader.getIcon("/nodes/c_protected.svg")),
    C_public(IconLoader.getIcon("/nodes/c_public.svg")),
    Class(IconLoader.getIcon("/nodes/class.svg")),
    ClassInitializer(IconLoader.getIcon("/nodes/classInitializer.svg")),
    CompiledClassesFolder(IconLoader.getIcon("/nodes/compiledClassesFolder.svg")),
    CopyOfFolder(IconLoader.getIcon("/nodes/copyOfFolder.svg")),
    Cvs_global(IconLoader.getIcon("/nodes/cvs_global.svg")),
    Cvs_roots(IconLoader.getIcon("/nodes/cvs_roots.svg")),
    DataColumn(IconLoader.getIcon("/nodes/dataColumn.svg")),
    DataSchema(IconLoader.getIcon("/nodes/dataSchema.svg")),
    dataTables(IconLoader.getIcon("/nodes/DataTables.svg")),
    Deploy(IconLoader.getIcon("/nodes/deploy.svg")),
    Ejb(IconLoader.getIcon("/nodes/ejb.svg")),
    EjbBusinessMethod(IconLoader.getIcon("/nodes/ejbBusinessMethod.svg")),
    EjbCmpField(IconLoader.getIcon("/nodes/ejbCmpField.svg")),
    EjbCmrField(IconLoader.getIcon("/nodes/ejbCmrField.svg")),
    EjbCreateMethod(IconLoader.getIcon("/nodes/ejbCreateMethod.svg")),
    EjbFinderMethod(IconLoader.getIcon("/nodes/ejbFinderMethod.svg")),
    EjbPrimaryKeyClass(IconLoader.getIcon("/nodes/ejbPrimaryKeyClass.svg")),
    EjbReference(IconLoader.getIcon("/nodes/ejbReference.svg")),
    EnterpriseProject(IconLoader.getIcon("/nodes/enterpriseProject.svg")),
    Enum(IconLoader.getIcon("/nodes/enum.svg")),
    ErrorIntroduction(IconLoader.getIcon("/nodes/errorIntroduction.svg")),
    ExceptionClass(IconLoader.getIcon("/nodes/exceptionClass.svg")),
    ExtractedFolder(IconLoader.getIcon("/nodes/extractedFolder.svg")),
    Field(IconLoader.getIcon("/nodes/field.svg")),
    FieldPK(IconLoader.getIcon("/nodes/fieldPK.svg")),
    Folder(IconLoader.getIcon("/nodes/folder.svg")),
    Function(IconLoader.getIcon("/nodes/function.svg")),
    HomeFolder(IconLoader.getIcon("/nodes/homeFolder.svg")),
    IdeaModule(IconLoader.getIcon("/nodes/ideaModule.svg")),
    IdeaProject(IconLoader.getIcon("/nodes/ideaProject.svg")),
    InspectionResults(IconLoader.getIcon("/nodes/inspectionResults.svg")),
    Interface(IconLoader.getIcon("/nodes/interface.svg")),
    JarDirectory(IconLoader.getIcon("/nodes/jarDirectory.svg")),
    JavaDocFolder(IconLoader.getIcon("/nodes/javaDocFolder.svg")),
    Jsr45(IconLoader.getIcon("/nodes/jsr45.svg")),
    KeymapAnt(IconLoader.getIcon("/nodes/keymapAnt.svg")),
    KeymapEditor(IconLoader.getIcon("/nodes/keymapEditor.svg")),
    KeymapMainMenu(IconLoader.getIcon("/nodes/keymapMainMenu.svg")),
    KeymapOther(IconLoader.getIcon("/nodes/keymapOther.svg")),
    KeymapTools(IconLoader.getIcon("/nodes/keymapTools.svg")),
    Method(IconLoader.getIcon("/nodes/method.svg")),
    Module(IconLoader.getIcon("/nodes/Module.svg")),
    ModuleGroup(IconLoader.getIcon("/nodes/moduleGroup.svg")),
    NewException(IconLoader.getIcon("/nodes/newException.svg")),
    NewFolder(IconLoader.getIcon("/nodes/newFolder.png")),
    NewParameter(IconLoader.getIcon("/nodes/newParameter.svg")),
    NodePlaceholder(IconLoader.getIcon("/nodes/nodePlaceholder.svg")),
    Package(IconLoader.getIcon("/nodes/package.svg")),
    Padlock(IconLoader.getIcon("/nodes/padlock.svg")),
    Parameter(IconLoader.getIcon("/nodes/parameter.svg")),
    Plugin(IconLoader.getIcon("/nodes/plugin.svg")),
    PluginNotInstalled(IconLoader.getIcon("/nodes/pluginnotinstalled.svg")),
    PluginObsolete(IconLoader.getIcon("/nodes/pluginobsolete.svg")),
    Pointcut(IconLoader.getIcon("/nodes/pointcut.svg")),
    PpFile(IconLoader.getIcon("/nodes/ppFile.png")),
    PpInvalid(IconLoader.getIcon("/nodes/ppInvalid.svg")),
    PpJar(IconLoader.getIcon("/nodes/ppJar.svg")),
    PpJdk(IconLoader.getIcon("/nodes/ppJdk.svg")),
    PpLib(IconLoader.getIcon("/nodes/ppLib.svg")),
    PpLibFolder(IconLoader.getIcon("/nodes/ppLibFolder.svg")),
    PpWeb(IconLoader.getIcon("/nodes/ppWeb.svg")),
    Project(IconLoader.getIcon("/nodes/project.svg")),
    Property(IconLoader.getIcon("/nodes/property.svg")),
    PropertyRead(IconLoader.getIcon("/nodes/propertyRead.svg")),
    PropertyReadStatic(IconLoader.getIcon("/nodes/propertyReadStatic.svg")),
    PropertyReadWrite(IconLoader.getIcon("/nodes/propertyReadWrite.svg")),
    PropertyReadWriteStatic(IconLoader.getIcon("/nodes/propertyReadWriteStatic.svg")),
    PropertyWrite(IconLoader.getIcon("/nodes/propertyWrite.svg")),
    PropertyWriteStatic(IconLoader.getIcon("/nodes/propertyWriteStatic.svg")),
    ReadAccess(IconLoader.getIcon("/nodes/read-access.svg")),
    ResourceBundle(IconLoader.getIcon("/nodes/resourceBundle.svg")),
    RwAccess(IconLoader.getIcon("/nodes/rw-access.svg")),
    SecurityRole(IconLoader.getIcon("/nodes/SecurityRole.png")),
    Servlet(IconLoader.getIcon("/nodes/servlet.svg")),
    SortBySeverity(IconLoader.getIcon("/nodes/sortBySeverity.svg")),
    Static(IconLoader.getIcon("/nodes/static.svg")),
    TestSourceFolder(IconLoader.getIcon("/nodes/testSourceFolder.svg")),
    Undeploy(IconLoader.getIcon("/nodes/undeploy.svg")),
    UnknownJdk(IconLoader.getIcon("/nodes/unknownJdk.svg")),
    UpFolder(IconLoader.getIcon("/nodes/upFolder.svg")),
    UpLevel(IconLoader.getIcon("/nodes/upLevel.svg")),
    Variable(IconLoader.getIcon("/nodes/variable.svg")),
    WarningIntroduction(IconLoader.getIcon("/nodes/warningIntroduction.svg")),
    WebFolder(IconLoader.getIcon("/nodes/webFolder.svg")),
    WebListener(IconLoader.getIcon("/nodes/weblistener.svg")),
    WriteAccess(IconLoader.getIcon("/nodes/write-access.svg"));

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
}
